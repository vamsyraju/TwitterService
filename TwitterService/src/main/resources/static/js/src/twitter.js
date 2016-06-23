$(document).ready(function() {
	 $('#tweetDataTable').DataTable( {
	 ajax: {
         url: "http://localhost:8080/getTimeLines?userName=vamsy_raju&sizeLimit=5",
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