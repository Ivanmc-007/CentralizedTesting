<#include "security.ftl">
<#import "logout.ftl" as logout>

<nav class="navbar navbar-dark bg-dark navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Centralized testing</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <#if myUserId == -1>
                    <li class="nav-item active">
                        <a class="nav-link" href="/registration">Registration</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/login">Login</a>
                    </li>
                <#else>
                    <li class="nav-item active">
                        <a class="nav-link" href="/myUser">My room</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="/myUser/profile">Profile</a>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item active">
                        <a class="nav-link" href="/admin">Admin room</a>
                    </li>
                </#if>

            </ul>

            <div class="navbar-text mr-3" >${myUserLogin}</div>

            <#if myUserId != -1>
                <div>
                    <@logout.logout/>
                </div>
            </#if>
        </div>

</nav>