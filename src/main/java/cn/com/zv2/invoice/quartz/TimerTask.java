package cn.com.zv2.invoice.quartz;

import cn.com.zv2.invoice.product.service.ProductService;
import cn.com.zv2.util.format.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author lb
 * @date 2019/10/29 2:19
 */
public class TimerTask {
    private static final Logger LOG = LoggerFactory.getLogger(TimerTask.class);

    private ProductService productService;
    private MailSender mailSender;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void updateProductPopularity() {
        System.out.println("TimerTask#updateProductPopularity>>>");
        LOG.info("TimerTask#updateProductPopularity>>>");
        productService.updateProductPopularity();
    }

    public void storageWarn() {
        System.out.println("TimerTask#storageWarn>>>");
        LOG.info("TimerTask#storageWarn>>>");
        System.out.println(">>>");
        System.out.println(mailSender);
        System.out.println("<<<");

        List<Object[]> warnProductList = productService.listWarnProduct();

        // 1.获取email发送器对象
        // 2.组织要发送的email信息内容
        // 3.发送email
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom("pau11uis_dev@163.com");
        smm.setTo("paulluis.dev@gmail.com");
        smm.setSubject("库存预警[" + DateUtils.formatDateTime(System.currentTimeMillis()) + "]");
        StringBuilder text = new StringBuilder();
        for (Object[] objArr : warnProductList) {
            BigInteger isUla = (BigInteger) objArr[1];
            if (isUla.intValue() == 1) {
                String productName = objArr[0].toString();
                text.append("商品【");
                text.append(productName);
                text.append("】库存超过预警值上限，请停止补货！\r\n");
                continue;
            }
            BigInteger isLla = (BigInteger) objArr[2];
            if (isLla.intValue() == 1) {
                String productName = objArr[0].toString();
                text.append("商品【");
                text.append(productName);
                text.append("】库存低于预警值下限，请及时补货！\r\n");
            }
        }
//        smm.setText("测试邮件...");
        System.out.println(text);
        smm.setText(text.toString());
        smm.setSentDate(new Date());
        mailSender.send(smm);
        System.out.println("send mail...");
    }
}
