<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<@common.commonPage>
    <@adminRoom.adminPage>
        <form action="/admin/choices" method="get">
            <div>С <input type="date" name="calendarStart"></div>
            <div>по <input type="date" name="calendarEnd"></div>
            <div><input type="submit" value="Search"></div>
        </form>
    </@adminRoom.adminPage>
</@common.commonPage>