
$(document).ready(function() {

    $('.secondaryImage').click(function(e) {

        const mainImage = $('#mainImage');
        const mainImageSrc = mainImage.attr('src');

        const targetSrc = e.target.src;

        mainImage.attr('src', targetSrc);
    });

    $("#add-product").submit(function(e) {

        e.preventDefault();

        const form = $(this);
        const URL = form.attr('action');
        const type = form.attr('method');

        $.ajax({
            type: type,
            url: URL,
            data: form.serialize(),
            success: function(data) {
                initPopUp(data);
                setTimeout(closePopUp, 2000);
            },
            error: function(data) {
                initPopUp(data.responseJSON);
                setTimeout(closePopUp, 5000);
            }
        });
    });

    function closePopUp() {
        $('#addProductPopUp').css('display', 'none');
    }

    function initPopUp(response) {

        $('#addProductPopUp').css('display', 'block');

        if (response.errorCodes.length > 0) {

            $('#addProductPopUp').html(
                `<div id="addProductPopUpContent"><span>${response.errorCodes[0]}</span> <span class="closePopUp">&times;</span></div>`
            );

        } else {
            $('#addProductPopUp').html(
                `<div id="addProductPopUpContent"><span>${response.successCode}</span> <span class="closePopUp">&times;</span></div>`
            );
        }

        $(".closePopUp").click(function(e) { closePopUp() });


    }




});
