<#macro universitiesShow universities>
    <#if universities?? && universities?has_content>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">id</th>
                <th scope="col">University name</th>
                <th scope="col">Action1</th>
                <th scope="col">Action2</th>
                <th scope="col">Action3</th>
            </tr>
            </thead>
            <tbody>

            <#list universities as university>
                <tr>
                    <td>${university.id}</td>
                    <td>${university.name}</td>
                    <form action="/admin/universities/remove" method="post">
                        <td>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <button type="submit" class="btn btn-dark" name="universityId" value="${university.id}">Remove</button>
                        </td>
                    </form>

                    <form action="/admin/universities/rename" method="get">
                        <td>
                            <button type="submit" class="btn btn-dark" name="universityId" value="${university.id}">Rename</button>
                        </td>
                    </form>

                    <form action="/admin/universities/setSubjects" method="get">
                        <td>
                            <button type="submit" class="btn btn-dark" name="universityId" value="${university.id}">Set subjects</button>
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