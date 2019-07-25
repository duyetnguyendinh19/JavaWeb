

CREATE TABLE IF NOT EXISTS `qlnv`.`department` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `qlnv`.`employee` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `address` LONGTEXT NOT NULL,
  `birthday` DATETIME NOT NULL,
  `level` VARCHAR(64) NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  `idDepartment` INT(11) NOT NULL,
  `email` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_o58xv1eg6nwb5du5xua34c1sr` (`idDepartment` ASC) ,
  CONSTRAINT `FK_o58xv1eg6nwb5du5xua34c1sr`
    FOREIGN KEY (`idDepartment`)
    REFERENCES `qlnv`.`department` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `qlnv`.`account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(64) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `employee_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_account_employee1_idx` (`employee_id` ASC) ,
  CONSTRAINT `fk_account_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `qlnv`.`employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `qlnv`.`discipline` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(256) CHARACTER SET 'utf8' NOT NULL,
  `describe` VARCHAR(256) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `reason` VARCHAR(45) NULL DEFAULT NULL,
  `date` DATE NOT NULL,
  `idEmployee` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_discipline_employee_idx` (`idEmployee` ASC) ,
  CONSTRAINT `fk_discipline_employee`
    FOREIGN KEY (`idEmployee`)
    REFERENCES `qlnv`.`employee` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `qlnv`.`contract` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `expirationday` DATETIME NOT NULL,
  `salary` FLOAT NOT NULL,
  `startday` DATETIME NOT NULL,
  `idEmployee` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_41uo76i8br4y4d3q8sr2ejrao` (`idEmployee` ASC) ,
  CONSTRAINT `FK_41uo76i8br4y4d3q8sr2ejrao`
    FOREIGN KEY (`idEmployee`)
    REFERENCES `qlnv`.`employee` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `qlnv`.`bonus` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(256) CHARACTER SET 'utf8' NOT NULL,
  `describe` VARCHAR(256) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `reason` VARCHAR(45) NULL DEFAULT NULL,
  `date` DATE NOT NULL,
  `idEmployee` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bonus_employee_idx` (`idEmployee` ASC) ,
  CONSTRAINT `fk_bonus_employee`
    FOREIGN KEY (`idEmployee`)
    REFERENCES `qlnv`.`employee` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;




