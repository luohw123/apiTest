#!/bin/sh
for((x=0;x<20;x++))
do
	curl -sd "username=a&password=1234562&captcha=a" "http://106.38.204.68:8010/login.aspx" |xargs echo '68'
	curl -sd "username=a&password=1234562&captcha=a" "http://106.38.204.83:8010/login.aspx" |xargs echo '204.83'
	curl -sd "username=a&password=1234562&captcha=a" "http://106.38.204.84:8010/login.aspx" |xargs echo '84'
	curl -sd "username=a&password=1234562&captcha=a" "http://111.202.61.164:8010/login.aspx" |xargs echo '164'
	curl -sd "username=a&password=1234562&captcha=a" "http://111.202.74.83:8010/login.aspx" |xargs echo '74.83'
	echo '-------'
done
