#!/bin/bash

SYSTEM=`uname -s`    #获取操作系统类型，我本地是linux

if [ $SYSTEM = "Linux" ] ; then     #如果是linux的话打印linux字符串
echo "Linux"
elif [ $SYSTEM = "FreeBSD" ] ; then
echo "FreeBSD"
elif [ $SYSTEM = "Solaris" ] ; then
echo "Solaris"
elif [ $SYSTEM = "Darwin" ] ; then
echo "Darwin"
else
echo `uname -s`;
fi     #ifend

echo '-----------'
