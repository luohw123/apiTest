#!/bin/sh
for((x=0;x<20;x++))
do
	curl  -H "Host:www.acfun.tv" -sd "username=a&password=1234562&captcha=a" "http://106.38.204.66:80/login.aspx"  |xargs echo '66'
	curl  -H "Host:www.acfun.tv" -sd "username=a&password=1234562&captcha=a" "http://106.38.204.82:80/login.aspx" |xargs echo '204.82'
	curl  -H "Host:www.acfun.tv" -sd "username=a&password=1234562&captcha=a" "http://111.202.61.162:80/login.aspx" |xargs echo '162'
	curl  -H "Host:www.acfun.tv" -sd "username=a&password=1234562&captcha=a" "http://111.202.74.82:80/login.aspx" |xargs echo '74.82'
	echo '-------'
done
