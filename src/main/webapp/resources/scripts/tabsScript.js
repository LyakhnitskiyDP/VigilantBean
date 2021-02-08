
$(document).ready(function() {

    $("#tabs").children()
              .each(function() {

                hideAllTabContents();

                $(this).click(function() {

                    hideAllTabContents();

                    unHighlightAllTabs();

                    $(this).toggleClass("highlighted");

                    $("#" + $(this).prop('id') + '-content').removeClass("hidden");
                })

              });
});

function unHighlightAllTabs() {

    $("#tabs").children()
              .each(function() {
                $(this).removeClass("highlighted");
              })
}

function hideAllTabContents() {

   $("#tab-content").children()
                    .each(function() {
                       $(this).addClass("hidden");
                    });
}



