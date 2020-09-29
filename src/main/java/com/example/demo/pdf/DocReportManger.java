//
//
//import com.inre.api.keyvalue.Prop;
//import com.inre.api.keyvalue.Record;
//import com.inre.core.page.Page;
//import com.inre.humantask.persistence.domain.TaskInfo;
//import com.inre.ver.persistence.domain.ReportModifyLog;
//import com.inre.ver.persistence.domain.ReportVersionUpdateLog;
//import com.inre.ver.persistence.domain.VerTaskDetail;
//import com.inre.ver.util.DateUtil;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.*;
//
//@Service
//public class DocReportManger {
//    @Resource
//    JdbcTemplate jdbcTemplate;
//
//    @Resource
//    VerTaskDetailManager verTaskDetailManager;
//
//    /**
//     * 修改记录单查询
//     *
//     * @param taskIds
//     * @return
//     */
//    public Map<String, Object> GetExportModifyLog(String taskIds) {
//        List<VerTaskDetail> list = new ArrayList<VerTaskDetail>();
//        if (taskIds != null && !taskIds.equals("")) {
//            String sqlQuery = "SELECT * FROM ver_task_detail WHERE find_in_set(id, ?)";
//            String sqlUpdate = "update ver_task_detail set record_export_at=? WHERE find_in_set(id, ?)";
//            list = jdbcTemplate.query(sqlQuery, new BeanPropertyRowMapper<>(VerTaskDetail.class), taskIds);
//            jdbcTemplate.update(sqlUpdate, new Date(), taskIds);
//        }
//
//        List<ReportModifyLog> list2 = new ArrayList<ReportModifyLog>();
//        if (list != null && list.size() > 0) {
//            for (VerTaskDetail item : list) {
//                ReportModifyLog reportModifyLog = new ReportModifyLog();
//                reportModifyLog.setTaskNo(item.getTaskNo());
//                reportModifyLog.setDescribe(item.getTaskDescription());
//                reportModifyLog.setRecordDate(DateUtil.dateString(item.getRecordExportAt()));
//                reportModifyLog.setTaskPerson(item.getBugConnectPerson());
//                reportModifyLog.setResult(item.getTaskHandleResult());
//                reportModifyLog.setSolution(item.getTaskSolution());
//                list2.add(reportModifyLog);
//            }
//        }
//
//        Map<String, Object> maps = new HashMap<String, Object>();
//        maps.put("recordDate", DateUtil.dateString(new Date()));
//        maps.put("passPerson", "");
//        maps.put("list", list2);
//        return maps;
//    }
//
//    /**
//     * 修改记录单 demo，仅用于测试
//     *
//     * @return
//     */
//    public Map<String, Object> GetExportModifyLog() {
//        Map<String, Object> maps = new HashMap<String, Object>();
//        List<ReportModifyLog> list = new ArrayList<ReportModifyLog>();
//        maps.put("recordDate", DateUtil.dateString(new Date()));
//        maps.put("passPerson", "经办人");
//        for (int i = 0; i < 10; i++) {
//            ReportModifyLog item = new ReportModifyLog();
//            item.setTaskNo("00188");
//            item.setDescribe("加入MOBAppKey校验，非法MOBAppKey将无法使用分享授权功能\n" +
//                    "2、QQ平台新增小程序分享、QZone本地视频分享、强制web/二维码授权，QQSDK更新到最新版本\n" +
//                    "3、优化instagram平台错误反馈\n" +
//                    "4、修复Twitter视频分享未带文字参数问题");
//            item.setTaskPerson("处理人");
//            item.setSolution("1、 新增抖音平台含分享、授权\n" +
//                    "2、优化配置加载时效\n" +
//                    "3、调整WhatsApp分享\n" +
//                    "4、修复使用分平台号，导致unity插件授权可能存在的问题\n" +
//                    "5、及其他平台优化");
//            item.setResult("消缺时间：2020年8月8日前解决");
//            list.add(item);
//        }
//        maps.put("list", list);
//        return maps;
//    }
//
//    /**
//     * 分页查询任务列表，在导出修改记录单页面显示，报表附件2的列表
//     *
//     * @param params
//     * @param page
//     * @return
//     */
//    public Page getTaskListByPage(Map<String, String> params, Page page) {
//        // 构建sql
//        String sql = " FROM ver_task_detail WHERE 1=1";
//        List<Object> queryParams = new ArrayList<Object>();
//        if (params.get("LIKES_bugTitle") != null && !params.get("LIKES_bugTitle").equals("")) {
//            sql += " and bug_title like ?";
//            queryParams.add("%" + params.get("LIKES_bugTitle") + "%");
//        }
//        if (params.get("START_recordExportAt") != null && !params.get("START_recordExportAt").equals("")) {
//            sql += " and create_at>=?";
//            queryParams.add(params.get("START_recordExportAt"));
//        }
//        if (params.get("END_recordExportAt") != null && !params.get("END_recordExportAt").equals("")) {
//            //sql += " and record_export_at<?";
//            sql += " and create_at<?";
//            queryParams.add(params.get("END_recordExportAt"));
//        }
//        if (params.get("STATUS_recordExport") != null && !params.get("STATUS_recordExport").equals("")) {
//            if (params.get("STATUS_recordExport").equals("yes")) {
//                sql += " AND(record_export_at is not null)";
//            } else {
//                sql += " AND(record_export_at is null)";
//            }
//        }
//
//        // 固定条件
//        sql += " AND task_status='归并驳回'";
//        String cntSql = "select count(*) as cnt " + sql;
//        Long cnt = jdbcTemplate.queryForObject(cntSql, Long.class, queryParams.toArray());
//        page.setTotalCount(cnt);
//
//        // 分页
//        String listSql = "select * " + sql + " order by id desc" + " limit " + page.getPageSize() * (page.getPageNo() - 1) + "," + page.getPageSize();
//        List<VerTaskDetail> list = jdbcTemplate.query(listSql, new BeanPropertyRowMapper<>(VerTaskDetail.class), queryParams.toArray());
//        page.setResult(list);
//        return page;
//    }
//
//    /**
//     * 版本更新记录单查询，报表附件5的列表
//     *
//     * @param params
//     * @param page
//     * @return
//     */
//    public Page getVersionListByPage(Map<String, String> params, Page page) {
//
//        // 构建sql
//        String cond = " from task_info T join account_info C on T.ASSIGNEE=C.ID where 1=1 ";
//        List<Object> queryParams = new ArrayList<Object>();
//        if (params.get("userId") != null && !params.get("userId").equals("")) {
//            cond += " and ASSIGNEE=?";
//            queryParams.add(params.get("userId"));
//        }
//
//        // 开始、结束时间查询
//        if (params.get("START_date") != null && !params.get("START_date").equals("")) {
//            cond += " and T.COMPLETE_TIME>=?";
//            queryParams.add(params.get("START_date"));
//        }
//        if (params.get("END_date") != null && !params.get("END_date").equals("")) {
//            cond += " and T.COMPLETE_TIME<?";
//            queryParams.add(params.get("END_date"));
//        }
//
//        // 导出状态
//        if (params.get("STATUS_recordExport") != null && !params.get("STATUS_recordExport").equals("")) {
//            if (params.get("STATUS_recordExport").equals("yes")) {
//                cond += " AND(T.ATTR1 is not null)";
//            } else {
//                cond += " AND(T.ATTR1 is null)";
//            }
//        }
//
//        // 固定条件
//        cond += " AND T.status='complete' AND T.name = '系统更新'";
//        String cntSql = "select count(*) as cnt " + cond;
//        Long cnt = jdbcTemplate.queryForObject(cntSql, Long.class, queryParams.toArray());
//        page.setTotalCount(cnt);
//
//        // 分页
//        String listSql = "select T.PRESENTATION_SUBJECT, T.ID,T.NAME,T.PROCESS_INSTANCE_ID,T.ATTR1,T.COMPLETE_TIME,C.DISPLAY_NAME as ATTR2 " + cond + " order by id desc" + " limit " + page.getPageSize() * (page.getPageNo() - 1) + "," + page.getPageSize();
//        List<TaskInfo> list = jdbcTemplate.query(listSql, new BeanPropertyRowMapper<>(TaskInfo.class), queryParams.toArray());
//        page.setResult(list);
//        return page;
//    }
//
//    /**
//     * 版本更新记录单 demo 仅用于测试
//     *
//     * @return
//     */
//    public Map<String, Object> GetExportVersionUpdateLog(String ids) {
//        // 1.更新导出时间
//        String sqlUpdate = "update task_info set ATTR1=? WHERE find_in_set(id, ?)";
//        jdbcTemplate.update(sqlUpdate, new Date(), ids);
//
//        // 2.找出报表数据列表
//        // 2.1 找出流程信息
//        String taskInfoSql = "select * from task_info where find_in_set(id, ?)";
//        List<TaskInfo> list = jdbcTemplate.query(taskInfoSql, new BeanPropertyRowMapper<>(TaskInfo.class), ids);
//        List<ReportVersionUpdateLog> reportList = new ArrayList<ReportVersionUpdateLog>();
//        ReportVersionUpdateLog reportVersionUpdateLog = null;
//        Record record = null;
//        String recordSql = "select * from kv_record where BUSINESS_KEY=?";
//        String propSql = "select * from kv_prop where RECORD_ID=?";
//        Integer seqNo = 0;
//
//        // 2.2找出每一个流程中的任务、版本信息
//        for (TaskInfo item : list) {
//            reportVersionUpdateLog = new ReportVersionUpdateLog();
//            //(1)序号
//            seqNo++;
//            reportVersionUpdateLog.setNo(seqNo.toString());
//            //(4)完成时间
//            reportVersionUpdateLog.setUpdateDate(DateUtil.dateString(item.getCompleteTime()));
//
//            // 根据BUSINESS_KEY在kv_record表查找Record,找到record id
//            record = jdbcTemplate.queryForObject(recordSql, new BeanPropertyRowMapper<>(Record.class), item.getBusinessKey());
//
//            // 找出属性集合
//            List<Prop> props = jdbcTemplate.query(propSql, new BeanPropertyRowMapper<>(Prop.class), record.getBusinessKey());
//            for (Prop prop : props) {
//                if (prop.getCode().equals("upgradeOldVersion")) {
//                    //(2)之前版本号
//                    String oldVersion = prop.getValue();
//                    if (oldVersion != null && !oldVersion.equals("")) {
//                        oldVersion = oldVersion.replace(",", "<w:p></w:p>");
//                        reportVersionUpdateLog.setPreviousVersion(oldVersion);
//                    } else {
//                        reportVersionUpdateLog.setPreviousVersion("");
//                    }
//                } else if (prop.getCode().equals("planNoList")) {
//                    //reportVersionUpdateLog.setNo(prop.getValue());
//                    //查询任务列表
//                    Map<String, String> maps = new HashMap<String, String>();
//                    maps.put("planNo", prop.getValue());
//                    List<VerTaskDetail> taskList = verTaskDetailManager.getSelectTaskList(maps);
//                    String describe = "";
//                    for (VerTaskDetail verTaskDetail : taskList) {
//                        describe += String.format("问题编号：%s<w:p></w:p>%s<w:p></w:p>", verTaskDetail.getTaskNo(), verTaskDetail.getBugTitle());
//                    }
//                    //(3)版本更新和消缺说明
//                    reportVersionUpdateLog.setUpdateDescribe(describe);
//                } else if (prop.getCode().equals("upgradeNewVersion")) {
//                    //(5)之前版本号
//                    String curVersion = prop.getValue();
//                    if (curVersion != null && !curVersion.equals("")) {
//                        curVersion = curVersion.replace(",", "<w:p></w:p>");
//                        reportVersionUpdateLog.setCurVersion(curVersion);
//                    } else {
//                        reportVersionUpdateLog.setCurVersion("");
//                    }
//                }
//            }
//
//            // (6)系统管理员确认签字
//            reportVersionUpdateLog.setSignature("");
//
//            reportList.add(reportVersionUpdateLog);
//        }
//
//        Map<String, Object> maps = new HashMap<String, Object>();
//        maps.put("recordDate", DateUtil.dateString(new Date()));
//        maps.put("passPerson", "");
//        maps.put("list", reportList);
//        return maps;
//    }
//
//    /**
//     * 版本更新记录单 demo 仅用于测试
//     *
//     * @return
//     */
//    public Map<String, Object> GetExportVersionUpdateLog() {
//        Map<String, Object> maps = new HashMap<String, Object>();
//        List<ReportVersionUpdateLog> list = new ArrayList<ReportVersionUpdateLog>();
//        for (int i = 0; i < 10; i++) {
//            ReportVersionUpdateLog item = new ReportVersionUpdateLog();
//            item.setNo("00188");
//            item.setPreviousVersion("1.0.0.1");
//            item.setUpdateDescribe("1、 新增抖音平台含分享、授权\n" +
//                    "2、优化配置加载时效\n" +
//                    "3、调整WhatsApp分享\n" +
//                    "4、修复使用分平台号，导致unity插件授权可能存在的问题\n" +
//                    "5、及其他平台优化");
//            item.setUpdateDate(DateUtil.dateString(new Date()));
//            item.setCurVersion("1.0.0.2");
//            item.setSignature("签字人");
//            list.add(item);
//        }
//        maps.put("list", list);
//        return maps;
//    }
//}