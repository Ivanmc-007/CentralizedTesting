<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/userRoom.ftl" as userRoom>
<#import "parts/modalWinYesNo.ftl" as modalWin>
<#import "parts/subjectShow.ftl" as subjectShow>

<@common.commonPage>
    <@userRoom.userPage>
        <#if choices??>
            <@subjectShow.subjectShow choices/>
        </#if>

        <#if messageWarningSubject??>
            <div class="alert alert-warning" role="alert">
                ${messageWarningSubject}
            </div>
        </#if>

        <#if !universitiesOfSubject??>
            <h3>Add new subject</h3>
            <form action="/myUser/subject" method="get">
                <#if subjectsDB??>
                    <select class="form-control col mt-2 <#if universitiesOfSubject??>form-control</#if>" name="subjectId">
                        <#list subjectsDB as subjectDB>-->
                            <div class="col">
                                <option value="${subjectDB.id}">${subjectDB.name}</option>
                            </div>
                        </#list>
                    </select>
                <#else>
                    <div>List subjects from DB is empty!</div>
                </#if>
                <button type="submit" class="btn btn-dark mt-2">Accept subject and select the university</button>
            </form>
        </#if>

        <#if universitiesOfSubject??>
            <h3>Select the university</h3>
            <form action="/myUser/subject" method="post">
                    <select class="form-control col mt-2" name="universityIdSelected">
                        <#list universitiesOfSubject as university>-->
                            <div class="col">
                                <option value="${university.id}">${university.name}</option>
                            </div>
                        </#list>
                    </select>
                <input type="hidden" name="subjectIdSelected" value="${subjectIdSet}" >
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-dark mt-2">Save</button>
            </form>
        </#if>

    </@userRoom.userPage>
</@common.commonPage>