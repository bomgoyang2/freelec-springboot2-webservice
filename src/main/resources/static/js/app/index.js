var main = {
    init : function(){
        var _this = this;
        $('#btn-save').on('click', function(){ _this.save(); });

        // btn-update란 id를 가진 HTML엘리먼트에 클릭 이벤트가 발생할 때 update function을 실행하도록 이벤트를 등록합니다.
        $('#btn-update').on('click', function(){ _this.update(); });

        $('#btn-delete').on('click', function(){ _this.delete() });
    },

    save : function(){
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다.');
            window.location.href = '/'; // 글 등록이 성공하면 메인페이지(/)로 이동합니다.
        }).fail(function(error){
            alert(JSON.stringify(errer));
        })
    },

    update : function(){
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type : 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(errer));
        });
    },

    delete : function(){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dateType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();