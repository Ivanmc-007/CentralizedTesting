<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>
<@common.commonPage>
    <div>
        <@login.login/>
    </div>
<#--    <form action="/login" method="post">-->
<#--        <div><label> User Name : <input type="text" name="username"/> </label></div>-->
<#--        <div><label> Password : <input type="password" name="password"/> </label></div>-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
<#--        <div><input type="submit" value="Sign In"/></div>-->
<#--    </form>-->
</@common.commonPage>
