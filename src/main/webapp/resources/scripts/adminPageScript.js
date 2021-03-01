$(document).ready(function() {

    refreshCategories();

    $(".ajaxForm").submit(function(e) {

        e.preventDefault();

        var form = $(this)[0];
        var formData = new FormData(form);
        var url = $(this).attr('action');
        var method = $(this).attr('method');
        var errorPane = $('.error-pane', form);

        $.ajax({
           type: method,
           url: url,
           data: formData,
           enctype: 'multipart/form-data',
           processData: false,
           contentType: false,
           cache: false,
           success: function(data) {
            errorPane.html("<p class='success'>" + data.successCode + "</p>")
            form.reset();
           },
           error: function(data) {

            let errors = '';
            $.each(data.responseJSON.errorCodes, function( i, error) {
                errors += "<p class='validation-error'>" + error + "</p>";
            });

            errorPane.html(errors);
           }
         });

    });

    $('#category-photo-upload-button').click(function(e) {

        e.preventDefault();
        $('#newCategoryPhoto')[0].click()
    });

});

function refreshCategories() {
    $.ajax({
        type: 'GET',
        url: 'api/category/allCategories',
        success: function(data) {
            let categoriesFieldContent = '';
            $.each(data, function(i, category) {
               let categoryName = category.name;
               let categoryShortName = category.shortName;
               categoriesFieldContent += '<div class="product-category"><label>' + categoryName +
                                             '</label><input type="checkbox" name="category_' + categoryShortName +
                                             '" value="' + categoryName + '" /></div>';
            });
            $("#categories-input-field").html(categoriesFieldContent);
        }
    });
}

function getSpringMessage(url) {

    if (url === 'admin/addCategory')
        return "<spring:message code='view.admin.addCategory.success' />";

    if (url === 'admin/addProduct')
        return "<spring:message code='view.admin.addProduct.success' />";
}

function previewCategoryImage() {
    let fileReader = new FileReader();
    fileReader.readAsDataURL(document.getElementById("newCategoryPhoto").files[0]);

    fileReader.onload = function(event) {
        document.getElementById("newCategory-photoPreview").src = event.target.result;
    };
}

