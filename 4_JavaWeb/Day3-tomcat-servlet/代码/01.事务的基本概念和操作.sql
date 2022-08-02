-- 1.禁用自动提交
SET autocommit = FALSE ;

-- 2.开启事务
START TRANSACTION ;
UPDATE t_person SET address='USA' WHERE id = 8 ;
DELETE FROM t_person WHERE id = 9 ; 
COMMIT;
-- rollback;