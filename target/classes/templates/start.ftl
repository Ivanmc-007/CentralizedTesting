<#import "parts/common.ftl" as common>
<@common.commonPage>
    <h1>Welcome!</h1>
    <div>Click <a href="/myUser">here</a> to see a greeting.</div>

    <#if messageActivation??>
        <div class="alert alert-success" role="alert">
            ${messageActivation}
        </div>
    </#if>
</@common.commonPage>