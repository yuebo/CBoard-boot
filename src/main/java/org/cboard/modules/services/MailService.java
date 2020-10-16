package org.cboard.modules.services;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.cboard.modules.pojo.DashboardJob;
import org.cboard.modules.services.persist.PersistContext;
import org.cboard.modules.services.persist.excel.XlsProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by yfyuan on 2017/2/10.
 *
 * @author WangKun
 * @date 2019/04/12
 */
@Service
public class MailService {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    public void sendDashboard(DashboardJob job) throws Exception {
        LOG.info("try to send mail");
    }
}
