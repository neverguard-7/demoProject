package com.example.demo.pdf;



import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import scala.collection.immutable.Page;
import scala.util.parsing.json.JSON;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * 报表相关查询页面和实际导出功能处理
 *
 * @author zhou.jin-qiao
 */
@Controller
@RequestMapping("/ver")
public class ExportDocController {



    /**
     * 修改记录单 列表页
     *
     * @param request
     * @param model
     * @param taskStatus
     * @param page
     * @return
     */
    @RequestMapping("/ver-task-cancel-list")
    public String taskCancelList(HttpServletRequest request, Model model,
                                 @RequestParam(value = "taskStatus", required = false) String taskStatus,
                                 @ModelAttribute Page page) {

        // 构建查询条件，放入到一个字典中
        Map<String, String> params = new HashMap<String, String>();
        params.put("LIKES_bugTitle", request.getParameter("LIKES_bugTitle"));
        params.put("START_recordExportAt", request.getParameter("START_recordExportAt"));
        params.put("END_recordExportAt", request.getParameter("END_recordExportAt"));
        params.put("STATUS_recordExport", request.getParameter("STATUS_recordExport"));

//        page.setOrder("DESC");    //降序
//        page.setOrderBy("id");
//        page = docReportManger.getTaskListByPage(params, page);
        model.addAttribute("params", params);
        model.addAttribute("page", page);
        return "ver/ver-task-cancel-list";
    }

    public static void main(String[] args) throws IOException {
        new ExportDocController().exportDoc(null, null);
    }

    /**
     * 修改记录单（附件2）、版本更新记录（附件5）等导出word
     * 后续有其它导入也可以加入到该方法中，统一处理
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/export-doc-file")
    public String exportDoc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 接收外部参数，其中type是业务类型，表示要导出哪一个附件
        String type = request.getParameter("type");
        type = "modify";
        // 其它业务中要用到的参数，如任务编号，主要依据该参数来获取报表中的数据
        String taskIds = request.getParameter("taskIds");

        // 定义通用的变量和对象
        File file = null;
        String fileName = null;
        InputStream fin = null;
        ServletOutputStream out = null;
        Map<String, Object> maps = new HashMap<>();

        // 查询数据、放入模板、生成临时文件、输出文件流
        try {
            // 修改记录单报表（附件2）,查询出要加载到模板中的数据
            if (type.equals("modify")) {
                //(1)查找出需要的数据
//                maps = docReportManger.GetExportModifyLog(taskIds);
                //(2)把数据和模板撮合起来（使用freeMark)

                List<pdfBean> arrayList= new ArrayList<>();
                arrayList.add(new pdfBean());
                arrayList.add(new pdfBean());
                arrayList.add(new pdfBean());
                arrayList.add(new pdfBean());

                maps.put("list", arrayList);
                maps.put("projectName", "测试项目超长不重复且重要并深沉需谨慎为稳定待测试");
                maps.put("number", 777);

                file = WordUtil.createDoc(maps, "tempV1.ftl");
                //(3)导出时的文件名
                fileName = new Date() + URLEncoder.encode("整改单", "UTF-8") + ".doc";
            }
            // 版本更新记录（附件5）,查询出要加载到模板中的数据
            else if (type.equals("versionUpdate")) {
//                maps = docReportManger.GetExportVersionUpdateLog(taskIds);
                file = WordUtil.createDoc(maps, "versionUpdateLog.ftl");
                fileName = new Date() + URLEncoder.encode("导出版本更新记录单", "UTF-8") + ".doc";
            }
            // to do
            /*
            else if(type.equals("typeFlag")
            {
                maps = xx;
                file = WordUtil.createDoc(maps, "xx.ftl");
                fileName = DateUtil.dateString(new Date()) + URLEncoder.encode("xx", "UTF-8") + ".doc";
            }*/

            // 文件导出为流，然后清除
            fin = new FileInputStream(file);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            out = response.getOutputStream();
            byte[] buffer = new byte[1024];//缓冲区
            int bytesToRead = -1;

            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fin != null) {
                fin.close();
            }
            if (out != null) {
                out.close();
            }
            if (file != null) {
                //删除临时文件
                file.delete();
            }
        }
        return null;
    }

    /**
     * 版本更新记录单 列表页
     *
     * @param request
     * @param model
     * @param taskStatus
     * @param page
     * @return
     */
    @RequestMapping("ver-report-version-update")
    public String versionUpdate(HttpServletRequest request, Model model,
                                @RequestParam(value = "taskStatus", required = false) String taskStatus,
                                @ModelAttribute Page page) {
        // 构建查询条件
        Map<String, String> params = new HashMap<String, String>();
        //        params.put("LIKES_bugTitle", request.getParameter("LIKES_bugTitle"));
        params.put("START_date", request.getParameter("START_date"));
//        params.put("END_date", DateUtil.parseEndDate(request.getParameter("END_date")));
//        params.put("STATUS_recordExport", request.getParameter("STATUS_recordExport"));
//
//        page.setOrder("DESC");    //降序
//        page.setOrderBy("id");
//        String userId = currentUserHolder.getUserId();
//        params.put("userId", userId);
//        page = docReportManger.getVersionListByPage(params, page);
//        model.addAttribute("params", params);
//        model.addAttribute("page", page);
        return "ver/ver-report-version-update";
    }


    public class pdfBean{
        public pdfBean() {
            this.positionName = "test";
            this.limitDate = new Date().toString();
            this.subPersonName = "subname";
            this.mainPersonName = "mainPersonName";
            this.createDate = new Date().toString();
            this.content = "content";
        }

        private String positionName;

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getLimitDate() {
            return limitDate.toString();
        }

        public void setLimitDate(String limitDate) {
            this.limitDate = limitDate;
        }

        public String getSubPersonName() {
            return subPersonName;
        }

        public void setSubPersonName(String subPersonName) {
            this.subPersonName = subPersonName;
        }

        public String getMainPersonName() {
            return mainPersonName;
        }

        public void setMainPersonName(String mainPersonName) {
            this.mainPersonName = mainPersonName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private String limitDate;
                private String subPersonName;
                private String mainPersonName;
                private String createDate;
                private String content;
    }
}
