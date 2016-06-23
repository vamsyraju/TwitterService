$(document).ready(function() {
	 $('#example').DataTable( {
	 ajax: {
         url: "http://localhost:8080/getTimeLines?userName=salesforce&sizeLimit=10",
         type: "GET"
     },
     columns: [
         { data: "userName" },
         { data: "screenName" },
         { data: "profileImage" },
         { data: "tweetContent" },
         { data: "retweetCount" }
     ]
	 });
	 
});