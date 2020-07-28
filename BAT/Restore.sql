--Restore completa BD
USE [master]
RESTORE DATABASE [dbMXAlert] 
RESTORE DATABASE [dbMXAlert] FROM DISK = N'C:\Users\ANTONIO-LIBORIO\Documents\NetBeansProjects\MxAlertDesktop\BAT\dbMXAlert.bak'  
WITH REPLACE
GO