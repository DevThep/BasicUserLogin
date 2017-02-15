$('#confirm-delete').on('click', '.btn-ok', function(e) {
    var $title = $(".modal-body .title").val();
    // console.log($title)
    toDelete($title);
});
$('#confirm-delete').on('show.bs.modal', function(e) {
    var data = $(e.relatedTarget).data();
    $('.title', this).text(data.recordTitle);
    $('.title', this).val(data.recordTitle);
});

$('#confirm-delete').on('hide.bs.modal', function(e) {
    var data = $(e.relatedTarget).data();
    $('.title', this).val("");
});

function toDelete(user){
    $.ajax({
        type: 'POST',
        url: '/user',
        data: { user: user },
        success: function(data) {
            if(data==="bad"){
                console.log("delete unsuccessful")
            }else{
                console.log("Deleted");
                window.location = "/user";
            }
        },
        error: function() {
            // $output.html("Username or password invalid");
            console.log("NO POST");
        }
    });
}
