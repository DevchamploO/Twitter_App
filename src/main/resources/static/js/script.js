/* ***add to favorites*** */
function set_like(id){
    var $id = id;
    $.post('/api/like/' + $id, function(data, status){
	        console.log('status' + ' : ' + 'liked ' + $id);
	    })
}

/* ***send a reply*** */
var reply = {};
var $message = $('#textbox');
var user;

//set id for reply object
function set_rUser(id, name){
user = '@' + name;
    reply.tweetId = id;
    $('#name').html(user);
}


//AJAX post request
$('#twitter_action').click(function(){
    reply.message = user + ' ' + $message.val();

    $.ajax({
    type: 'POST',
    contentType: 'application/json',
    dataType: 'json',
    url: '/api/reply',
    data: JSON.stringify(reply),
    success: function(result){
    console.log('reply successful :: ' + result);
    reply = {};
        }
    });
});

/* ***send a retweet*** */
function doRetweet(id){
    console.log(id);
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        url: '/api/retweet/' + id,
        data: JSON.stringify(reply),
        success: function(result){
            console.log('retweet successful');
        }
    });
}

/* ***delete a status*** */
function doDelete(id){
    $.post('/api/delete/' + id, function(data, status){
	        console.log('deletion of ' + id);
	    });
}

/* ***send a direct message*** */
$('#DM_action').click(function(){
    var $tweetId = $('#tweetId').val();
    var $message = $('#message').val();
    reply.tweetId = $tweetId;
    reply.message = $message;

    $.ajax({
    type: 'POST',
    contentType: 'application/json',
    dataType: 'json',
    url: '/api/direct_message',
    data: JSON.stringify(reply),
    success: function(result){
    console.log('reply successful :: ' + result);
        }
    });
});