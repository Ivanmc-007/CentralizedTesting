<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
    myUser = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    myUserLogin = myUser.getLogin()
    myUserId = myUser.getId()
    hasDocument = myUser.hasDocument()
    isAdmin = myUser.isAdmin()
    >
<#else>
    <#assign
    myUserLogin = "unknown"
    myUserId = -1
    hasDocument = false
    isAdmin = false
    >
</#if>