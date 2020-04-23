<#include "security.ftl">
<#macro adminPage>
    <div class="row">
        <div class="col-3">
            <nav class="nav flex-column">
                <ul class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action" href="/admin">Hello admin</a>
                    <a class="list-group-item list-group-item-action" href="/admin/subjects">Subjects</a>
                    <a class="list-group-item list-group-item-action" href="/admin/universities">Universities</a>
                    <a class="list-group-item list-group-item-action" href="/admin/users">Users</a>
                    <a class="list-group-item list-group-item-action" href="/admin/choices">Choices</a>
<#--                    <a class="list-group-item list-group-item-action" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">-->
<#--                        Search by</a>-->
<#--                    <div class="dropdown-menu" aria-labelledby="dropdownMenu2">-->
<#--                        <a class="dropdown-item" href="/admin/search/date">date added</a>-->
<#--                        <a class="dropdown-item" href="#">Action2</a>-->
<#--                        <a class="dropdown-item" href="#">Action3</a>-->
<#--                    </div>-->

                </ul>
            </nav>
        </div>

        <div class="col">
            <#nested>
        </div>
    </div>
</#macro>