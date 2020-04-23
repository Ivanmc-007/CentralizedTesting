<#import "modalWinYesNo.ftl" as modalWin>
<#macro subjectShow choices>
    <h3>Your subjects:</h3>
    <#if choices??>
        <table class="table">
            <#if choices?has_content>
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Subject name</th>
                    <th scope="col">University</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
            </#if>
            <tbody>
            <#list choices! as choice>
                <tr>
                    <th scope="row">#</th>
                    <td>${choice.subject.name}</td>
                    <td>${choice.university.name}</td>
                    <td>
                        <form action="/myUser/subject/removeSubject" method="get">
                            <button type="submit" class="btn btn-dark" data-toggle="modal"
                                data-target="#modalWindow" name="choiceId" value="${choice.id}">Remove
                            </button>
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

        <#if choices?size == 0>
            <div>You haven't subjects!</div>
        </#if>
    <#else>
        <div>You haven't subjects!</div>
    </#if>
</#macro>

<#macro subjectShowBySubject subjects>
    <#if subjects?? && subjects?has_content>
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Subject name</th>
                    <th scope="col">Action1</th>
                    <th scope="col">Action2</th>
                    <th scope="col">Action3</th>
                </tr>
            </thead>
            <tbody>

                <#list subjects as subject>
                    <tr>
                        <td>${subject.id}</td>
                        <td>${subject.name}</td>
                        <form action="/admin/subjects/remove" method="post">
                            <td>
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <button type="submit" class="btn btn-dark" name="subjectId" value="${subject.id}">Remove</button>
                            </td>
                        </form>

                        <form action="/admin/subjects/rename" method="get">
                            <td>
                                <button type="submit" class="btn btn-dark" name="subjectId" value="${subject.id}">Rename</button>
                            </td>
                        </form>

                        <form action="/admin/subjects/setUniversities" method="get">
                            <td>
                                <button type="submit" class="btn btn-dark" name="subjectId" value="${subject.id}">Set universities</button>
                            </td>
                        </form>
                    </tr>
                </#list>
            </tbody>
        </table>
    <#else>
        <div>You haven't subjects!</div>
    </#if>

</#macro>