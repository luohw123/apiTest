遍历:
<#list nihaolist as list >
索引:${list_index} 索引相应的值:${list}
<#if list_index == 1>
输出索引为1得时候
</#if>

</#list>

索引为2时候:${nihaolist[2]}
