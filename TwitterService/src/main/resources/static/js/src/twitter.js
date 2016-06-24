$(document).ready(function() {
	 var table=$('#twitterTable')
	 .on( 'error.dt', function ( e, settings, techNote, message )  {
		 console.log(message);
		 $(location).attr('href', 'http://localhost:8080/socialMediaError?message=Internal Error. Please contact admin@admin.com, Error:'+message);
	 } )
	 .DataTable( {
	 ajax: {
         url: "http://localhost:8080/getTimeLines?userName=salesforce&sizeLimit=20",
         type: "GET",
     },
     columns: [
               { data: "profileImage","render": function(data, type, row) {
                   return '<img src="'+data+'" height="35" width="35"/>';
               },"width": "5%" },
               { data: "userName" ,"width": "10%"},
               { data: "screenName","width": "10%" },
               { data: "retweetCount","width": "10%"},
               { data: "tweetContent","width": "40%" }
     ],
     columnDefs: [
                  	{ className: "dt-body-center", "targets": [ 0]},
                  	{ className: "dt-body-center", "targets": [ 1]},
                    { className: "dt-body-center", "targets": [ 2]},
                    { className: "dt-body-center", "targets": [ 3 ]}
                  ]
	 });
	 setInterval( function () {
         table.ajax.reload(); 
         }, 60000 );
	 $('#twitterDiv').append("<div>Sales Force Twitter</div>");
});	