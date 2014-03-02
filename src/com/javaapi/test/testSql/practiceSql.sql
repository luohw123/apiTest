select * from sns_wall;
select * from sns_wall left join sns_social on sns_wall.snsId=sns_social.Id;
select * from sns_wall right join sns_social on sns_wall.snsId=sns_social.Id;

select count(1) from sns_social left join sns_wall on sns_wall.snsId=sns_social.id

select * from sns_wall where sns_wall.sort =1 order by sns_wall.time desc;

-- 统计sort得总数量
select sns_wall.sort ,count(1) from sns_wall
left join sns_social on sns_wall.snsId=sns_social.id 
group by sort;

select sns_wall.sort,count(1),sns_wall.time from sns_wall where sns_wall.time
between '2012-12-08' AND '2012-12-16' group by  sns_wall.sort ;