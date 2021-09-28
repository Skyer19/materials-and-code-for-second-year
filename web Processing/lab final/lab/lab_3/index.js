 $(document).ready(function(){
        $('.video_pic').mouseover(function(){
            $('.mask').show();
        });
        $(`.video_pic`).mouseout(function(){
            $('.mask').hide();
        });

    });
