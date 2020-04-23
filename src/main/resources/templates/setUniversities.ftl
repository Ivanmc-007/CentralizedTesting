<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<@common.commonPage>
    <@adminRoom.adminPage>
        <div class="col mt-3">
            Subject id:
            <label class="font-weight-bold">${subject.id}</label>, subject name:
            <label class="font-weight-bold">${subject.name}</label>
        </div>
        <div class="col mt-3">List of universities:</div>
        <#if universities??>
            <form action="/admin/subjects/setUniversities" method="post">
            <#list universities as university>
            <div class="col mt-3">
                <label><input type="checkbox" name="universityId" value="${university.id}" ${universitiesOfSubject?seq_contains(university)?string("checked","")}>${university.name}</label>
            </div>

            </#list>
                <div class="col mt-3"><button type="submit" class="btn btn-dark">Accept</button></div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="subjectId" value="${subject.id}" />
            </form>
            <#else>
            <#--            todo: Alert -->
                <div>List of universities is empty!</div>
        </#if>


    </@adminRoom.adminPage>
</@common.commonPage>