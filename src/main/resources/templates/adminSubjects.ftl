<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<#import "parts/modalWinYesNo.ftl" as modalWin>
<#import "parts/subjectShow.ftl" as subjectShow>
<@common.commonPage>
    <@adminRoom.adminPage>
        <#if messageSuccessfulAdd??>
            <div class="alert alert-success" role="alert">
                ${messageSuccessfulAdd}
            </div>
        </#if>

        <#if subjectAddError??>
            <div class="alert alert-danger" role="alert">
                ${subjectAddError}
            </div>
        </#if>

        <@subjectShow.subjectShowBySubject subjects></@subjectShow.subjectShowBySubject>

        <h3>Fill in the fields</h3>
        <form method="post" action="/admin/subjects/add">
            <div class="form-group">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="text" class="form-control mb-3 ${(subjectNameError??)?string('is-invalid','')}" name="subjectName" placeholder="Subject name"/>
                <#if subjectNameError??>
                    <div class="invalid-feedback">
                        ${subjectNameError}
                    </div>
                </#if>
                <button type="submit" class="btn btn-dark mb-5">Add subject</button>
            </div>
        </form>

    </@adminRoom.adminPage>
</@common.commonPage>