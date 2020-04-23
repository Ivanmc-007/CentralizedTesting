<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<@common.commonPage>
    <@adminRoom.adminPage>
        <h3>Hello dear administrator!</h3>
        <div>Администратор, у вас нет прав доступа!
        Обратитесь к администратору!</div>
    </@adminRoom.adminPage>
</@common.commonPage>