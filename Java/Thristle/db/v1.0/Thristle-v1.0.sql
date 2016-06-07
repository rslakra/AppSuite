/******************************************************************************
 * This script prepares the physical 'Thristle' database schema.
 * 
 * Database : [Thristle]
 * Author: Rohtash Singh (rohtash.singh@devmatre.com)
 * Create On : 05/19/2016
 *  
 * Updated On : 05/19/2016
 * Updated By : Rohtash Singh Lakra
 * 
 * Purpose : Prepares the core database schema. This script was created based 
 * 	on the database backup dump or assumption basis which can be modified later.
 *  If there are some changes in the schema's of other database(s), this script 
 * 	might need to update to make it compatible for library management.
 * 
 * Outputs	: Thristle appication utilizes this database.
 * Permissions	: Access (read/write permissions)
 * Release	: v1.0.0
 ******************************************************************************/
/*
 * Create the physical database and grant permissions to root user.
 * 
 * Note: - These commands must execute by root user.
 */
DROP DATABASE IF EXISTS Thristle;
CREATE DATABASE IF NOT EXISTS Thristle;
/*
 * Note: - These commands must execute by root user.
 */
GRANT ALL ON Thristle.* to 'singhr'@'localhost' IDENTIFIED by 'Singhr!16';
GRANT ALL PRIVILEGES ON *.* TO 'singhr'@'localhost' IDENTIFIED BY 'Singhr!16' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'singhr'@'%' IDENTIFIED BY 'Singhr!16' WITH GRANT OPTION;


/*
 * Use Database
 */
USE Thristle;


/*
 * Drop Table(s)
 */
DROP TABLE IF EXISTS `Organizations`;
DROP TABLE IF EXISTS `Addresses`;
DROP TABLE IF EXISTS `Cities`;
DROP TABLE IF EXISTS `States`;
DROP TABLE IF EXISTS `Countries`;
DROP TABLE IF EXISTS `Continents`;
DROP TABLE IF EXISTS `UnitDesignators`;
DROP TABLE IF EXISTS `RolesModules`;
DROP TABLE IF EXISTS `Users`;
DROP TABLE IF EXISTS `Modules`;
DROP TABLE IF EXISTS `Roles`;
DROP TABLE IF EXISTS `Contents`;
DROP TABLE IF EXISTS `Feedbacks`;
DROP TABLE IF EXISTS `Comments`;
DROP TABLE IF EXISTS `Blogs`;


/*
 * Continents Table
 * 
 * Name: Name of the continent
 * Area: Total area of the continent
 */
CREATE TABLE IF NOT EXISTS `Continents` (
	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Name` VARCHAR(50) NOT NULL,
  	`Area` VARCHAR(5) NOT NULL,
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Insert default records
 * 
 * SELECT * FROM Continents;
 */
INSERT INTO Continents (Name, Area) VALUES('Asia', '0');
INSERT INTO Continents (Name, Area) VALUES('Africa', '0');
INSERT INTO Continents (Name, Area) VALUES('North America', '0');
INSERT INTO Continents (Name, Area) VALUES('South America', '0');
INSERT INTO Continents (Name, Area) VALUES('Antarctica', '0');
INSERT INTO Continents (Name, Area) VALUES('Europe', '0');
INSERT INTO Continents (Name, Area) VALUES('Australia', '0');
/*
 * INSERT INTO Continents (Name, Area) VALUES('Eurasia', '0');
 */

/*
 * Countries Table
 */
CREATE TABLE IF NOT EXISTS `Countries` (
	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Name` VARCHAR(50) NOT NULL,
  	`Code` VARCHAR(5) NOT NULL,
 	`ContinentId` BIGINT NOT NULL,
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Add Foreign Key Constraints/Relations
 */
ALTER TABLE `Countries`
ADD CONSTRAINT `FK_Countries_Continents`
FOREIGN KEY (`ContinentId`)
REFERENCES `Continents` (`Id`);

/*
 * Insert default records
 * 
 * SELECT * FROM Countries;
 */
INSERT INTO Countries (Name, Code, ContinentId)
VALUES('India', 'IN', 1);
INSERT INTO Countries(Name, Code, ContinentId)
VALUES('United State of America', 'USA', 3);


/*
 * States Table
 */
CREATE TABLE IF NOT EXISTS `States` (
	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Name` VARCHAR(50) NOT NULL,
  	`Code` VARCHAR(5) NOT NULL,
  	`CountryId` BIGINT NOT NULL,
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Add Foreign Key Constraints/Relations
 */
ALTER TABLE `States`
ADD CONSTRAINT `FK_States_Countries`
FOREIGN KEY (`CountryId`)
REFERENCES `Countries` (`Id`);

/*
 * Insert default records for India
 * 
 * SELECT * FROM States;
 */
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Andaman and Nicobar Islands', 'AN', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Andhra Pradesh', 'AP', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy) 
VALUES('Arunachal Pradesh' ,'AR', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Assam', 'AS', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Bihar', 'BR', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Chandigarh', 'CH', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Chhattisgarh', 'CT', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Dadra and Nagar Haveli', 'DN', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Daman and Diu', 'DD', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Delhi', 'DL' , 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Goa', 'GA', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Gujarat', 'GJ', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Haryana', 'HR', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Himachal Pradesh', 'HP', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Jammu and Kashmir', 'JK', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy) 
VALUES('Jharkhand' , 'JH', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Karnataka', 'KA', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Kerala', 'KL', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Lakshadweep', 'LD', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Madhya Pradesh', 'MP', 1,'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Maharashtra', 'MH', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Manipur', 'MN', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Meghalaya', 'ML', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Mizoram', 'MZ', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Nagaland', 'NL', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Orissa', 'OR', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Puducherry', 'PY', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Punjab', 'PB', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Rajasthan', 'RJ', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Sikkim', 'SK', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Tamil Nadu', 'TN', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Tripura', 'TR', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Uttarakhand', 'UT', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Uttar Pradesh', 'UP', 1, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('West Bengal', 'WB', 1, 'Rohtash Singh');

/*
 * Insert default records for USA
 * 
 * SELECT * FROM States;
 */
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Alabama', 'AL', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Alaska', 'AK', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Arizona', 'AZ', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Arkansas', 'AR', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('California', 'CA', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Colorado', 'CO', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Connecticut', 'CT', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Delaware', 'DE', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('District of Columbia', 'DC', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Florida', 'FL', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Georgia', 'GA', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Hawaii', 'HI', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Idaho', 'ID', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Illinois', 'IL', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Indiana', 'IN', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Iowa', 'IA', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Kansas', 'KS', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Kentucky', 'KY', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Louisiana', 'LA', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Maine', 'ME', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Maryland', 'MD', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Massachusetts', 'MA', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Michigan', 'MI', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Minnesota', 'MN', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Mississippi', 'MS', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Missouri', 'MO', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Montana', 'MT', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Nebraska', 'NE', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Nevada', 'NV', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('New Hampshire', 'NH', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('New Jersey', 'NJ', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('New Mexico', 'NM', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('New York', 'NY', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('North Carolina', 'NC', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('North Dakota', 'ND', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Ohio', 'OH', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Oklahoma', 'OK', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Oregon', 'OR', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Pennsylvania', 'PA', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Rhode Island', 'RI', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('South Carolina', 'SC', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('South Dakota', 'SD', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Tennessee', 'TN', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Texas', 'TX', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Utah', 'UT', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Vermont', 'VT', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Virginia', 'VA', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Washington', 'WA', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('West Virginia', 'WV', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Wisconsin', 'WI', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Wyoming', 'WY', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('American Samoa', 'AS', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Guam', 'GU', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Northern Mariana Islands', 'MP', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Puerto Rico', 'PR', 2, 'Rohtash Singh');
INSERT INTO States (Name, Code, CountryId, CreatedBy)
VALUES('Virgin Islands', 'VI', 2, 'Rohtash Singh');


/*
 * Cities Table
 */
CREATE TABLE IF NOT EXISTS `Cities` (
	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Name` VARCHAR(50) NOT NULL,
  	`ZipCode` VARCHAR(6) NOT NULL,
  	`StateId` BIGINT NOT NULL,
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Add Foreign Key Constraints/Relations
 */
ALTER TABLE `Cities`
ADD CONSTRAINT `FK_Cities_States`
FOREIGN KEY (`StateId`)
REFERENCES `States` (`Id`);

/*
 * Insert default records for Haryana
 * 
 * SELECT * FROM Cities;
 */
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Ambala', '133001', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Bhiwani', '000000', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Faridabad', '000000', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Fatehabad', '000000', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Gurgaon', '122001', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Hisar', '125001', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Jhajjar', '000000', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Jind', '000000', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Kaithal', '000000', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Karnal', '000000', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Kurukshetra', '000000', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Mahendragarh', '000000', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Panchkula', '000000', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Panipat', '132108', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Rewari', '123401', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Rohtak', '124001', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Sirsa', '000000', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Sonipat', '000000', '13', 'Rohtash Singh');
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Yamunanagar', '000000', '13', 'Rohtash Singh');

--California Cities
INSERT INTO Cities (Name, ZipCode, StateId, CreatedBy)
VALUES('Fremont', '94555', '40', 'Rohtash Singh');



/*
 * UnitDesignators Table
 */
CREATE TABLE IF NOT EXISTS `UnitDesignators` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Description` VARCHAR(50) NOT NULL,
  	`Abbreviation` VARCHAR(10) NOT NULL,
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Insert default records for Organization's Address
 * 
 * SELECT * FROM UnitDesignators;
 */
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Apartment', 'APT', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Basement', 'BSMT', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Blank', ' ', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Building', 'BLDG', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Department', 'DEPT', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Floor', 'FL', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Front', 'FRNT', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Hanger', 'HNGR', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Key', 'KEY', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Lobby', 'LBBY', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Lot', 'LOT', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Lower', 'LOWR', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Office', 'OFC', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Penthouse', 'PH', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Pier', 'PIER', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Rear', 'REAR', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Room', 'RM', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Side', 'SIDE', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Slip', 'SLIP', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Space', 'SPC', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Stop', 'STOP', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Suite', 'STE', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Trailer', 'TRLR', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Unit', 'UNIT', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Upper', 'UPPR', 'Rohtash Singh');
INSERT INTO UnitDesignators (Description, Abbreviation, CreatedBy)
VALUES('Opposite', 'OPP.', 'Rohtash Singh');


/*
 * Addresses Table
 */
CREATE TABLE IF NOT EXISTS `Addresses` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`UnitDesignatorId` BIGINT DEFAULT NULL,
  	`Street` VARCHAR(50) DEFAULT NULL,
  	`Province` VARCHAR(50) DEFAULT NULL,
  	`CityId` BIGINT NOT NULL,
  	`StateId` BIGINT NOT NULL,
  	`ZipCode` VARCHAR(10) DEFAULT NULL,
  	`CountryId` BIGINT NOT NULL,
  	`TelPhone` VARCHAR(16) DEFAULT NULL,
  	`MobilePhone` VARCHAR(16) DEFAULT NULL,
  	`Email` VARCHAR(50) DEFAULT NULL,
  	`WebSite` VARCHAR(50) DEFAULT NULL,
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Add Foreign Key Constraints/Relations
 */
ALTER TABLE `Addresses`
ADD CONSTRAINT `FK_Addresses_UnitDesignators`
FOREIGN KEY (`UnitDesignatorId`)
REFERENCES `UnitDesignators` (`Id`);

ALTER TABLE `Addresses`
ADD CONSTRAINT `FK_Addresses_Cities`
FOREIGN KEY (`CityId`)
REFERENCES `Cities` (`Id`);

ALTER TABLE `Addresses`
ADD CONSTRAINT `FK_Addresses_States`
FOREIGN KEY (`StateId`)
REFERENCES `States` (`Id`);

ALTER TABLE `Addresses`
ADD CONSTRAINT `FK_Addresses_Countries`
FOREIGN KEY (`CountryId`)
REFERENCES `Countries` (`Id`);

/*
 * Insert default records for Organization's Address
 * 
 * SELECT * FROM Addresses;
 */
INSERT INTO Addresses (UnitDesignatorId, Street, Province, CityId, StateId, ZipCode, CountryId, TelPhone, MobilePhone, Email, WebSite, CreatedBy)
VALUES(26, 'Talyar Lake', 'Asthal Bohar', 16, 13, '124001', 1, '+91-01262-251792', '+91-9416864189', 'info@vnmpsrohtak.com', 'www.vnmpsrohtak.com', 'Rohtash Singh');

INSERT INTO Addresses (UnitDesignatorId, Street, CityId, StateId, ZipCode, CountryId, TelPhone, MobilePhone, Email, WebSite, CreatedBy)
VALUES(24, '34593 Pueblo Ter', 16, 40, '94555', 2, '+1-201-238-6938', '+1-201-238-6938', 'rohtash.singh@gmail.com', 'www.devamatre.com', 'Rohtash Singh Lakra');


/*
 * OrganizationTypes Table
 */
CREATE TABLE IF NOT EXISTS `OrganizationTypes` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Type` VARCHAR(50) NOT NULL,
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Insert default records for OrganizationTypes
 * 
 * SELECT * FROM OrganizationTypes;
 */
INSERT INTO OrganizationTypes (Type) VALUES ('Information Technology');
INSERT INTO OrganizationTypes (Type) VALUES ('Real Estate');
INSERT INTO OrganizationTypes (Type) VALUES ('Education');
INSERT INTO OrganizationTypes (Type) VALUES ('Communication');
INSERT INTO OrganizationTypes (Type) VALUES ('Finance');
INSERT INTO OrganizationTypes (Type) VALUES ('Transportation');


/*
 * Organizations Table
 */
CREATE TABLE IF NOT EXISTS `Organizations` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Name` VARCHAR(50) NOT NULL,
  	`OrganizationTypeId` BIGINT NOT NULL,
  	`ShortName` VARCHAR(50) DEFAULT NULL,
  	`ExtraDetails` VARCHAR(100) DEFAULT NULL,
  	`Description` VARCHAR(255) DEFAULT NULL,
  	`AddressId` BIGINT NOT NULL,
  	`ParentId` BIGINT DEFAULT NULL,
 	`EmployerIdNumber` VARCHAR(50) NOT NULL DEFAULT 'XXX-XX-XXXX',
  	`EstablishedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Add Foreign Key Constraints/Relations
 */
ALTER TABLE `Organizations`
ADD CONSTRAINT `FK_Organizations_Organizations`
FOREIGN KEY (`ParentId`)
REFERENCES `Organizations` (`Id`);
--Address Foreign Key
ALTER TABLE `Organizations`
ADD CONSTRAINT `FK_Organizations_Addresses`
FOREIGN KEY (`AddressId`)
REFERENCES `Addresses` (`Id`);
--Address Foreign Key
ALTER TABLE `Organizations`
ADD CONSTRAINT `FK_Organizations_OrganizationTypes`
FOREIGN KEY (`OrganizationTypeId`)
REFERENCES `OrganizationTypes` (`Id`);

/*
 * Insert default records for Organizations
 * 
 * SELECT * FROM Organizations;
 */
INSERT INTO Organizations (Name, OrganizationTypeId, ExtraDetails, Description, AddressId)
VALUES('V. N. Memorial Public School', 3, '(Affiliated to C.B.S.E. Delhi Code No 530345)', 'The Education Institute', 1);


/*
 * Roles Table
 */
CREATE TABLE IF NOT EXISTS `Roles` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Name` VARCHAR(50) NOT NULL,
  	`Description` VARCHAR(255) DEFAULT NULL,
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Insert default records for Roles
 * 
 * SELECT * FROM Roles;
 */
INSERT INTO Roles(Name, Description, CreatedBy)
VALUES('ADMINISTRATOR','The Administrator who has unrestricted access to the application.', 'Rohtash Singh');
INSERT INTO Roles(Name, Description, CreatedBy)
VALUES('SUPERVISOR','The Supervisor who has restricted access to the application.', 'Rohtash Singh');
INSERT INTO Roles(Name, Description, CreatedBy)
VALUES('USER','The Users who have restricted access to the application.', 'Rohtash Singh');


/*
 * Modules Table
 */
CREATE TABLE IF NOT EXISTS `Modules` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Name` VARCHAR(50) NOT NULL,
  	`Description` VARCHAR(255) NULL,
  	`TargetPath` VARCHAR(255) NULL,
  	`ParentId` BIGINT DEFAULT NULL,
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Add Foreign Key Constraints/Relations
 */
ALTER TABLE `Modules`
ADD CONSTRAINT `FK_Modules_Modules`
FOREIGN KEY (`ParentId`)
REFERENCES `Modules` (`Id`);

/*
 * Insert default records for Modules
 * 
 * SELECT * FROM Modules;
 */
INSERT INTO Modules(Name, Description, CreatedBy)
VALUES('Modules','The root (parent) module of all other modules of an application.', 'Rohtash Singh');
INSERT INTO Modules(Name, Description, ParentId, CreatedBy)
VALUES('Setup','The setup modules to manage organization, users and other settings.', 1, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, ParentId, CreatedBy)
VALUES('User','The module which setup user details.', 2, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, ParentId, CreatedBy)
VALUES('Logout','The logout module to logout the users from an application.', 1, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, ParentId, CreatedBy) 
VALUES('WebSite', 'The Website module of an organization.', 1, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, ParentId, CreatedBy)
VALUES('Library','The library module which manages library activities of an organization.', 1, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, ParentId, CreatedBy)
VALUES('School','The school module handles the common school activities.', 1, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, ParentId, CreatedBy)
VALUES('Payroll','The payroll management module of an organization.', 1, 'Rohtash Singh');
SELECT * FROM  Modules;

INSERT INTO Modules(Name, Description, TargetPath, ParentId, CreatedBy) 
VALUES('Home', 'The website''s home page.', 'index.php', 5, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, TargetPath, ParentId, CreatedBy) 
VALUES('Contents', 'The website''s Contents page.', '', 5, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, TargetPath, ParentId, CreatedBy) 
VALUES('About', 'The website''s About page.', '', 5, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, TargetPath, ParentId, CreatedBy) 
VALUES('Contact', 'The website''s Contact page.', '', 5, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, TargetPath, ParentId, CreatedBy) 
VALUES('Blog', 'The blog''s page.', 'BlogIndex.php', 5, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, TargetPath, ParentId, CreatedBy) 
VALUES('Help', 'The website''s Help page.', '', 5, 'Rohtash Singh');


INSERT INTO Modules(Name, Description, TargetPath, ParentId, CreatedBy) 
VALUES('Add & Update', 'The website''s home page.', 'ContentIndex.php', 10, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, TargetPath, ParentId, CreatedBy) 
VALUES('Search', 'The website''s home page.', 'ContentSearch.php', 10, 'Rohtash Singh');

INSERT INTO Modules(Name, Description, TargetPath, ParentId, CreatedBy) 
VALUES('About Us', 'The website''s home page.', 'AboutUs.php', 11, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, TargetPath, ParentId, CreatedBy) 
VALUES('Gallery', 'The website''s home page.', 'ImageGalleryIndex.php', 11, 'Rohtash Singh');

INSERT INTO Modules(Name, Description, TargetPath, ParentId, CreatedBy) 
VALUES('Contact Us', 'The website''s home page.', 'ContactUs.php', 12, 'Rohtash Singh');
INSERT INTO Modules(Name, Description, TargetPath, ParentId, CreatedBy) 
VALUES('Customer Support', 'The website''s home page.', 'CustomerSupport.php', 12, 'Rohtash Singh');
SELECT * FROM  Modules;

/*
 * RolesModules Table
 */
CREATE TABLE IF NOT EXISTS `RolesModules` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`RoleId` BIGINT NOT NULL,
  	`ModuleId` BIGINT NOT NULL,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

/*
 * Add Foreign Key Constraints/Relations
 */
ALTER TABLE `RolesModules`
ADD CONSTRAINT `FK_RolesModules_Roles`
FOREIGN KEY (`RoleId`)
REFERENCES `Roles` (`Id`);

ALTER TABLE `RolesModules`
ADD CONSTRAINT `FK_RolesModules_Modules`
FOREIGN KEY (`ModuleId`)
REFERENCES `Modules` (`Id`);

/*
 * Insert default records for RolesModules (Administrator)
 */
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(1, 1);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(1, 2);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(1, 3);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(1, 4);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(1, 5);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(1, 6);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(1, 7);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(1, 8);

/*
 * Insert default records for RolesModules (Supervisor)
 */
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(2, 1);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(2, 2);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(2, 3);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(2, 4);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(2, 5);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(2, 6);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(2, 7);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(2, 8);

/*
 * Insert default records for RolesModules (Users)
 */
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(3, 1);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(3, 3);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(3, 4);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(3, 5);
INSERT INTO RolesModules(RoleId, ModuleId) VALUES(3, 8);

--select * from rolesmodules where roleid = 1;


/*
 * Users Table
 */
CREATE TABLE IF NOT EXISTS `Users` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`UserName` VARCHAR(50) NOT NULL,
  	`FirstName` VARCHAR(50) NOT NULL,
  	`MiddleName` VARCHAR(50) DEFAULT NULL,
  	`LastName` VARCHAR(50) NOT NULL,
  	`Email` VARCHAR(50) NOT NULL,
  	`Password` VARCHAR(50) NOT NULL,
  	`RoleId` BIGINT NOT NULL,
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Add Foreign Key Constraints/Relations
 */
ALTER TABLE `Users`
ADD CONSTRAINT `FK_Users_Roles`
FOREIGN KEY (`RoleId`)
REFERENCES `Roles` (`Id`);

/*
 * Insert default records for Users (Administrator)
 * 
 * MD5 Passord Rsingh=10336C13F4F6828A0E2609FB8D8C1745
 */
INSERT INTO Users(UserName, FirstName, LastName, Email, Password, RoleId, CreatedBy)
VALUES('rsingh','Rohtash', 'Singh', 'rohtash.singh@devamatre.com', '10336C13F4F6828A0E2609FB8D8C1745', 1, 'Rohtash Singh');


/*
 * Contents Table
 */
CREATE TABLE IF NOT EXISTS `Contents` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Title` VARCHAR(50) NOT NULL,
  	`Description` VARCHAR(2000) NULL,
  	`Price` DECIMAL(5, 2) NOT NULL,
  	`ImagePath` VARCHAR(50) NOT NULL,
  	`Type` VARCHAR(20) NOT NULL,
  	`Size` BIGINT SIGNED NOT NULL,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


/*
 * Feedbacks Table
 */
DROP TABLE IF EXISTS `Feedbacks`;
CREATE TABLE IF NOT EXISTS `Feedbacks` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Name` VARCHAR(100) NOT NULL,
  	`Email` VARCHAR(255) NOT NULL,
  	`ContactDetail` VARCHAR(100) DEFAULT NULL,
  	`Message` VARCHAR(1000) DEFAULT NULL,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Categories Table
 */
DROP TABLE IF EXISTS `Categories`;
CREATE TABLE IF NOT EXISTS `Categories` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Name` VARCHAR(100) NOT NULL,
  	`Description` VARCHAR(255) NOT NULL,
  	`Active` BIT NOT NULL DEFAULT 1,  	
  	`ParentId` BIGINT NULL,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Add Foreign Key Constraints/Relations
 */
ALTER TABLE `Categories`
ADD CONSTRAINT `FK_Categories_Categories`
FOREIGN KEY (`ParentId`)
REFERENCES `Categories` (`Id`);

/*
 * Blogs Table
 */
CREATE TABLE IF NOT EXISTS `Blogs` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Title` VARCHAR(255) NOT NULL,
  	`Message` VARCHAR(1000) DEFAULT NULL,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`Updatable` BIT NOT NULL DEFAULT 1,
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`Deletable` BIT NOT NULL DEFAULT 0,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Comments Table
 */
CREATE TABLE IF NOT EXISTS `Comments` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Comment` VARCHAR(1000) DEFAULT NULL,
  	`BlogId` BIGINT NOT NULL,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`Updatable` BIT NOT NULL DEFAULT 0,
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`Deletable` BIT NOT NULL DEFAULT 0,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Add Foreign Key Constraints/Relations
 */
ALTER TABLE `Comments`
ADD CONSTRAINT `FK_Comments_Blogs`
FOREIGN KEY (`BlogId`)
REFERENCES `Blogs` (`Id`);

/****************************************************************************
 * Library - create the physical database
 ****************************************************************************/
 DROP TABLE IF EXISTS `Status`;
/*
 * Status Table
 */
CREATE TABLE IF NOT EXISTS `Status` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Code` VARCHAR(5) NOT NULL,
  	`Description` VARCHAR(255) NOT NULL,
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Insert default records for India
 */
INSERT INTO Status (Code, Description, CreatedBy)
VALUES('AV', 'Available', 'Rohtash Singh');
INSERT INTO Status (Code, Description, CreatedBy)
VALUES('IT', 'In Transit', 'Rohtash Singh');
INSERT INTO Status (Code, Description, CreatedBy)
VALUES('CO', 'Checked Out', 'Rohtash Singh');
INSERT INTO Status (Code, Description, CreatedBy)
VALUES('OH', 'On Hold', 'Rohtash Singh');


/*
 * Media Table
 *
 * Description: -
 * (the media) the mass communication industry, esp. newspaper, television and radio; 
 * journalists and other related professionals collectively.
 * This list can enlarge based on requirements.
 */
DROP TABLE IF EXISTS `Media`;
CREATE TABLE IF NOT EXISTS `Media` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`Type` VARCHAR(10) NOT NULL,
  	`Description` VARCHAR(255) NOT NULL,
	`StatusId` BIGINT NOT NULL,  	
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL DEFAULT NULL,
  	`UpdatedBy` VARCHAR(50) DEFAULT NULL,
  	`DeletedOn` TIMESTAMP NULL DEFAULT NULL,
  	`DeletedBy` VARCHAR(50) DEFAULT NULL
);

/*
 * Add Foreign Key Constraints/Relations
 */
ALTER TABLE `Media`
ADD CONSTRAINT `FK_Media_Status`
FOREIGN KEY (`StatusId`)
REFERENCES `Status` (`Id`);

/*
 * Insert default records for Media
 */
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 1, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('CD', 'Compact Disk', 1, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 1, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 1, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 1, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 2, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 3, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 1, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 1, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 1, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 1, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 1, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 4, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 1, 'Rohtash Singh');
INSERT INTO Media (Type, Description, StatusId, CreatedBy)
VALUES('Book', 'Book Details', 1, 'Rohtash Singh');


/*
 * Books Table
 *
 * ISBN - If you have purchased an ISBN (International Standard Book Number) 
 *      for your eBook, please enter it here. ISBNs for print editions should 
 *      not be entered in this field.
 *
 * Title - Enter the title of your eBook here. If your eBook is part of a 
 *      series, you can enter the series title and/or volume number here.
 *
 * Description - Like the copy on the inside flap of a hardcover book, the 
 *      description tells readers a bit about your eBook. You can enter a 
 *      description for your eBook that is between 30 and 4000 characters 
 *      in length. Note: The book description on your title's detail page 
 *      may be delayed but should appear within 72 hours.
 *
 * Contributors - Enter the names of the people who contributed to your eBook, 
 *      including authors, editors, illustrators, translators, and more. At 
 *      least one author name is required. 
 *
 * Language - Enter the language in which your content appears. We currently 
 *      support English, French, German, Italian, and Portuguese.
 *
 * Publication Date - If your title has been previously published in physical 
 *      or digital format, enter the original publication date here. Otherwise, 
 *      the Kindle Store will automatically enter the date the item is being 
 *      published once everything has been processed.
 *
 * Publisher - Enter the publisher name that you would like listed for your 
 *      eBook on the book's detail page. This can be an individual or company 
 *      name.
 *
 * Verifying Your Publishing Rights - If your title is a Public Domain book 
 *      check This book is in the public domain. If the publishing rights to 
 *      your title are privately held check I hold the rights to this book. 
 *      We may refuse public domain content that is already available through 
 *      our program. To read more about our public domain policy, please see 
 *      our Content Guidelines.
 *
 * Categories - This information will help interested readers find your eBook.
 *      Choose from dozens of categories, from Antiques to True Crime and 
 *      everything in between. Each category also contains subcategories to 
 *      further classify your content.
 *      In order to provide a more succinct browsing experience for customers, 
 *      the number of categories you can select is limited to 2 browse 
 *      categories. In the past, publishers could select up to 5 browse 
 *      categories. Books that had been previously published with 5 browse 
 *      categories will retain their category assignments. To learn more about 
 *      selecting categories for your book, please visit our Browse Categories 
 *      help page.
 *
 * Search Keywords - Enter 5-7 descriptive keywords to help readers find your 
 *      eBook. Separate keywords using commas.
 *
 * Territory Rights - If you hold worldwide rights for your book, select the 
 *      Worldwide Rights option. This enables Amazon to make your book 
 *      available for sale worldwide.
 */
DROP TABLE IF EXISTS `Books`;
CREATE TABLE IF NOT EXISTS `Books` (
  	`Id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`ISBN` VARCHAR(14) NOT NULL,
  	`Title` VARCHAR(255) NOT NULL,
  	`Description` VARCHAR(255) NULL,
  	`Author` VARCHAR(255) NOT NULL,
  	`Languate` VARCHAR(255) NULL,
  	`PublishedOn` TIMESTAMP NULL,
  	`Publisher` VARCHAR(255) NULL,
  	`PublishingRights` VARCHAR(255) NULL,
  	`Categories` VARCHAR(255) NULL,
  	`SearchKeywords` VARCHAR(255) NULL,
  	`TerritoryRights` VARCHAR(255) NULL,
   	`Price` FLOAT NOT NULL,
  	`CurrencyType` VARCHAR(3) NOT NULL DEFAULT 'USD',
  	`Active` BIT NOT NULL DEFAULT 1,
  	`CreatedOn` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	`CreatedBy` VARCHAR(50) NOT NULL DEFAULT 'Rohtash Singh',
  	`UpdatedOn` TIMESTAMP NULL,
  	`UpdatedBy` VARCHAR(50),
  	`DeletedOn` TIMESTAMP NULL,
  	`DeletedBy` VARCHAR(50)
);

/*
 * Insert default records for Media
 */
INSERT INTO Books (ISBN, Title, Author, PublishedOn, Price, CreatedBy)
VALUES('978-0743289412', 'Lisey''s Story', 'Stephen King', '2006-01-01', 10.0, 'Rohtash Singh');
INSERT INTO Books (ISBN, Title, Author, PublishedOn, Price, CreatedBy)
VALUES('978-1596912366', 'Restless: A Novel', 'William Boyd', '2006-01-01', 10.0, 'Rohtash Singh');
INSERT INTO Books (ISBN, Title, Author, PublishedOn, Price, CreatedBy)
VALUES('978-0312351588', 'Beachglass', 'Wendy Blackburn', '2006-01-01', 10.0, 'Rohtash Singh');
INSERT INTO Books (ISBN, Title, Author, PublishedOn, Price, CreatedBy)
VALUES('978-0156031561', 'The Places In Between', 'Rory Stewart', '2006-01-01', 10.0, 'Rohtash Singh');
INSERT INTO Books (ISBN, Title, Author, PublishedOn, Price, CreatedBy)
VALUES('978-0060583002', 'The Last Season', 'Eric Blehm', '2006-01-01', 10.0, 'Rohtash Singh');
INSERT INTO Books (ISBN, Title, Author, PublishedOn, Price, CreatedBy)
VALUES('978-0316740401', 'Case Histories: A Novel', 'Kate Atkinson', '2006-01-01', 10.0, 'Rohtash Singh');
INSERT INTO Books (ISBN, Title, Author, PublishedOn, Price, CreatedBy)
VALUES('978-0316013949', 'Step on a Crack', 'James Patterson, et al.', '2007-01-01', 10.0, 'Rohtash Singh');
INSERT INTO Books (ISBN, Title, Author, PublishedOn, Price, CreatedBy)
VALUES('978-0374105235', 'Long Way Gone: Memoirs of a Boy Soldier', 'Ishmael Beah', '2007-01-01', 10.0, 'Rohtash Singh');
INSERT INTO Books (ISBN, Title, Author, PublishedOn, Price, CreatedBy)
VALUES('978-0385340229', 'Sisters', 'Danielle Steel', '2006-01-01', 10.0, 'Rohtash Singh');


//CREATE TABLE Status (code INTEGER, description CHAR(30), PRIMARY KEY (code));
//CREATE TABLE Media( media_id INTEGER, code INTEGER, PRIMARY KEY (media_id), FOREIGN KEY (code) REFERENCES Status );
//CREATE TABLE Book(ISBNCHAR(14), title CHAR(128), author CHAR(64), year INTEGER, dewey INTEGER, price REAL, PRIMARY KEY (ISBN) );
CREATE TABLE BookMedia( media_id INTEGER, ISBN CHAR(14), PRIMARY KEY (media_id),FOREIGN KEY (media_id) REFERENCES Media,FOREIGN KEY (ISBN) REFERENCES Book);
CREATE TABLE Customer( ID INTEGER, name CHAR(64), addr CHAR(256), DOB CHAR(10),phone CHAR(30), username CHAR(16), password CHAR(32), PRIMARY KEY (ID),UNIQUE (username) );
CREATE TABLE Card( num INTEGER, fines REAL, ID INTEGER, PRIMARY KEY (num),FOREIGN KEY (ID) REFERENCES Customer );
CREATE TABLE Checkout( media_id INTEGER, num INTEGER, since CHAR(10),until CHAR(10), PRIMARY KEY (media_id),FOREIGN KEY (media_id) REFERENCES Media,FOREIGN KEY (num) REFERENCES Card );
CREATE TABLE Location( name CHAR(64), addr CHAR(256), phone CHAR(30),PRIMARY KEY (name) );
CREATE TABLE Hold( media_id INTEGER, num INTEGER, name CHAR(64), until CHAR(10),queue INTEGER, PRIMARY KEY (media_id, num),FOREIGN KEY (name) REFERENCES Location,FOREIGN KEY (num) REFERENCES Card,FOREIGN KEY (media_id) REFERENCES Media );
CREATE TABLE Stored_In( media_id INTEGER, name char(64), PRIMARY KEY (media_id),FOREIGN KEY (media_id) REFERENCES Media ON DELETE CASCADE,FOREIGN KEY (name) REFERENCES Location );
CREATE TABLE Librarian( eid INTEGER, ID INTEGER NOT NULL, Pay REAL, Loc_name CHAR(64) NOT NULL, PRIMARY KEY (eid), FOREIGN KEY (ID) REFERENCES Customer ON DELETE CASCADE, FOREIGN KEY (Loc_name) REFERENCES Location(name) );
CREATE TABLE Video( title CHAR(128), year INTEGER, director CHAR(64), rating REAL, price REAL, PRIMARY KEY (title, year) );
CREATE TABLE VideoMedia( media_id INTEGER, title CHAR(128), year INTEGER,PRIMARY KEY (media_id), FOREIGN KEY (media_id) REFERENCES Media, FOREIGN KEY (title, year) REFERENCES Video );

/*
 * input some sample data
 */
INSERT INTO Customer(ID, name, addr, DOB, phone, username, password) 
VALUES (60201, 'Jason L. Gray', '2087 Timberbrook Lane, Gypsum, CO 81637','09/09/1958', '970-273-9237', 'jlgray', 'password1');
INSERT INTO Customer(ID, name, addr, DOB, phone, username, password) 
VALUES(89682, 'Mary L. Prieto', '1465 Marion Drive, Tampa, FL 33602','11/20/1961', '813-487-4873', 'mlprieto', 'password2');
INSERT INTO Customer(ID, name, addr, DOB, phone, username, password) 
VALUES(64937, 'Roger Hurst', '974 Bingamon Branch Rd, Bensenville, IL 60106','08/22/1973', '847-221-4986', 'rhurst', 'password3');
INSERT INTO Customer(ID, name, addr, DOB, phone, username, password) 
VALUES(31430, 'Warren V. Woodson', '3022 Lords Way, Parsons, TN 38363','03/07/1945', '731-845-0077', 'wvwoodson', 'password4');
INSERT INTO Customer(ID, name, addr, DOB, phone, username, password) 
VALUES(79916, 'Steven Jensen', '93 Sunny Glen Ln, Garfield Heights, OH 44125','12/14/1968', '216-789-6442', 'sjensen', 'password5');
INSERT INTO Customer(ID, name, addr, DOB, phone, username, password) 
VALUES(93265, 'David Bain', '4356 Pooh Bear Lane, Travelers Rest, SC 29690','08/10/1947', '864-610-9558', 'dbain', 'password6');
INSERT INTO Customer(ID, name, addr, DOB, phone, username, password) 
VALUES(58359, 'Ruth P. Alber', '3842 Willow Oaks Lane, Lafayette, LA 70507','02/18/1976', '337-316-3161', 'rpalber', 'password7');
INSERT INTO Customer(ID, name, addr, DOB, phone, username, password) 
VALUES(88564, 'Sally J. Schilling', '1894 Wines Lane, Houston, TX 77002','07/02/1954', '832-366-9035', 'sjschilling', 'password8');
INSERT INTO Customer(ID, name, addr, DOB, phone, username, password) 
VALUES(57054, 'John M. Byler', '279 Raver Croft Drive, La Follette, TN 37766','11/27/1954', '423-592-8630', 'jmbyler', 'password9');
INSERT INTO Customer(ID, name, addr, DOB, phone, username, password) 
VALUES(49312, 'Kevin Spruell', '1124 Broadcast Drive, Beltsville, VA 20705','03/04/1984', '703-953-1216', 'kspruell', 'password10');

INSERT INTO Card(num, fines, ID) VALUES ( 5767052, 0.0, 60201);
INSERT INTO Card(num, fines, ID) VALUES ( 5532681, 0.0, 60201);
INSERT INTO Card(num, fines, ID) VALUES ( 2197620, 10.0, 89682);
INSERT INTO Card(num, fines, ID) VALUES ( 9780749, 0.0, 64937);
INSERT INTO Card(num, fines, ID) VALUES ( 1521412, 0.0, 31430);
INSERT INTO Card(num, fines, ID) VALUES ( 3920486, 0.0, 79916);
INSERT INTO Card(num, fines, ID) VALUES ( 2323953, 0.0, 93265);
INSERT INTO Card(num, fines, ID) VALUES ( 4387969, 0.0, 58359);
INSERT INTO Card(num, fines, ID) VALUES ( 4444172, 0.0, 88564);
INSERT INTO Card(num, fines, ID) VALUES ( 2645634, 0.0, 57054);
INSERT INTO Card(num, fines, ID) VALUES ( 3688632, 0.0, 49312);

INSERT INTO Location(name, addr, phone) 
VALUES ('Texas Branch','4832 Deercove Drive, Dallas, TX 75208', '214-948-7102');
INSERT INTO Location(name, addr, phone) 
VALUES ('Illinois Branch','2888 Oak Avenue, Des Plaines, IL 60016', '847-953-8130');
INSERT INTO Location(name, addr, phone) 
VALUES ('Louisiana Branch','2063 Washburn Street, Baton Rouge, LA 70802', '225-346-0068');

INSERT INTO BookMedia(media_id, ISBN) VALUES (8733, '978-0743289412');
INSERT INTO BookMedia(media_id, ISBN) VALUES (9982, '978-1596912366');
INSERT INTO BookMedia(media_id, ISBN) VALUES (3725, '978-1596912366');
INSERT INTO BookMedia(media_id, ISBN) VALUES (2150, '978-0312351588');
INSERT INTO BookMedia(media_id, ISBN) VALUES (4188, '978-0156031561');
INSERT INTO BookMedia(media_id, ISBN) VALUES (5271, '978-0060583002');
INSERT INTO BookMedia(media_id, ISBN) VALUES (2220, '978-0316740401');
INSERT INTO BookMedia(media_id, ISBN) VALUES (7757, '978-0316013949');
INSERT INTO BookMedia(media_id, ISBN) VALUES (4589, '978-0374105235');
INSERT INTO BookMedia(media_id, ISBN) VALUES (5748, '978-0385340229');

INSERT INTO Checkout(media_id, num, since, until) 
VALUES(2220, 9780749, '02/15/2007', '03/15/2007');
INSERT INTO Video(title, year, director, rating, price) 
VALUES('Terminator 2: Judgment Day', 1991, 'James Cameron', 8.3, 20.0);
INSERT INTO Video(title, year, director, rating, price) 
VALUES('Raiders of the Lost Ark', 1981, 'Steven Spielberg', 8.7, 20.0);
INSERT INTO Video(title, year, director, rating, price) 
VALUES('Aliens', 1986, 'James Cameron', 8.3, 20.0);
INSERT INTO Video(title, year, director, rating, price) 
VALUES('Die Hard', 1988, 'John McTiernan', 8.0, 20.0);
INSERT INTO VideoMedia(media_id, title, year) 
VALUES( 1734, 'Terminator 2: Judgment Day', 1991);
INSERT INTO VideoMedia(media_id, title, year) 
VALUES( 5725, 'Raiders of the Lost Ark', 1981);
INSERT INTO VideoMedia(media_id, title, year) 
VALUES( 1716, 'Aliens', 1986);
INSERT INTO VideoMedia(media_id, title, year) 
VALUES( 8388, 'Aliens', 1986);
INSERT INTO VideoMedia(media_id, title, year) 
VALUES( 8714, 'Die Hard', 1988);

INSERT INTO Hold(media_id, num, name, until, queue) 
VALUES(1716, 4444172, 'Texas Branch', '02/20/2008', 1);

INSERT INTO Librarian(eid, ID, pay, Loc_name) 
Values(2591051, 88564, 30000.00, 'Texas Branch');
INSERT INTO Librarian(eid, ID, pay, Loc_name) 
Values(6190164, 64937, 30000.00, 'Illinois Branch');
INSERT INTO Librarian(eid, ID, pay, Loc_name) 
Values(1810386, 58359, 30000.00, 'Louisiana Branch');

INSERT INTO Stored_In(media_id, name) VALUES(8733, 'Texas Branch');
INSERT INTO Stored_In(media_id, name) VALUES(9982, 'Texas Branch');
INSERT INTO Stored_In(media_id, name) VALUES(1716, 'Texas Branch');
INSERT INTO Stored_In(media_id, name) VALUES(1734, 'Texas Branch');
INSERT INTO Stored_In(media_id, name) VALUES(4589, 'Texas Branch');
INSERT INTO Stored_In(media_id, name) VALUES(4188, 'Illinois Branch');
INSERT INTO Stored_In(media_id, name) VALUES(5271, 'Illinois Branch');
INSERT INTO Stored_In(media_id, name) VALUES(3725, 'Illinois Branch');
INSERT INTO Stored_In(media_id, name) VALUES(8388, 'Illinois Branch');
INSERT INTO Stored_In(media_id, name) VALUES(5748, 'Illinois Branch');
INSERT INTO Stored_In(media_id, name) VALUES(2150, 'Louisiana Branch');
INSERT INTO Stored_In(media_id, name) VALUES(8714, 'Louisiana Branch');
INSERT INTO Stored_In(media_id, name) VALUES(7757, 'Louisiana Branch');
INSERT INTO Stored_In(media_id, name) VALUES(5725, 'Louisiana Branch');

