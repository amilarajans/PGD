DROP TABLE `trn_course_payments`;

CREATE TABLE `trn_course_payments` (
  `id`            BIGINT       NOT NULL AUTO_INCREMENT,
  `refference`    VARCHAR(255) NULL,
  `fk_payment`    BIGINT       NULL,
  `fk_student`    BIGINT       NULL,
  `paymentDate`   DATETIME     NULL,
  `fk_pay_mode`   BIGINT       NULL,
  `remarks`       VARCHAR(255) NULL,
  `is_active`     BIT(1)       NULL,
  `created_by`    VARCHAR(50)  NULL,
  `created_date`  DATETIME     NULL,
  `modified_by`   VARCHAR(50)  NULL,
  `modified_date` DATETIME     NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cpay_pay_mode` FOREIGN KEY (`fk_pay_mode`) REFERENCES `mst_payment_mode` (`id`),
  CONSTRAINT `fk_cpay_student` FOREIGN KEY (`fk_student`) REFERENCES `mst_student` (`id`),
  CONSTRAINT `fk_cpay_payment` FOREIGN KEY (`fk_payment`) REFERENCES `mst_course_payments` (`id`),
  CONSTRAINT `fk_cpay_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_info` (`login`),
  CONSTRAINT `fk_cpay_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user_info` (`login`)
);