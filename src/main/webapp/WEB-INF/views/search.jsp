<div style="width: 90%!important; display:block; text-align:center; margin:0 auto">
<form action="/books/search" method="post">
    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
    <div class="form-row align-items-center">
        <button style="float: right" type="submit" class="btn btn-primary"><i class="fa fa-search" aria-hidden="true"></i> Search</button>
        <div class="col-auto" style="overflow: hidden; padding-right: .5em;">
            <input style="width: 100%;" class="form-control is-invalid" name="textSearch" placeholder="Enter ..."
                   oninvalid="this.setCustomValidity('Please, enter keywords for search !')"
                   oninput="setCustomValidity('')" required>
        </div>

        <br/>
        <div class="search-options">
            <input type="checkbox" name="byTitle" value="1"/> By title
            <input type="hidden" name="byTitle" value="0"/>

            <input style="margin-left: 15px" type="checkbox" name="byDescription" value="1"/> In description
            <input type="hidden" name="byDescription" value="0"/>
        </div>
    </div>
</form>
</div>

<p>
    the result is : ${searchValue} ${byTitleValue} ${byDescriptionValue}
</p>

