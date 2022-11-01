-- 코드를 입력하세요
#SELECT * from first_half
#select * from icecream_info
SELECT fh.flavor from first_half fh 
left join icecream_info ii on fh.FLAVOR = ii.FLAVOR
where total_order > 3000 and ii.ingredient_type = "fruit_based"
order by total_order desc