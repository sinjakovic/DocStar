var postings = angular.module('Docstar.Profile', ['ngRoute', 'ngResource', 'ui.bootstrap', 'Session']);

postings.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/profile', {
        templateUrl: 'resources/views/shared/profile/profile.html',
        controller: 'Docstar.Profile.Controller'
    });
}]);

postings.controller('Docstar.Profile.Controller', ['$scope', '$resource', '$uibModal', 'Session.Service', function($scope, $resource, $modal, Session) {
    $scope.user = Session.user();
    delete $scope.user.authorities;
    var User = $resource('api/v1/user/profile', {}, { 'update' : { method : 'PUT'} } );

    $scope.update = function(){
        if(window.FileReader){
            var fileInput = $('#file1');
            var fData = new FormData();
            console.log(fileInput);
            if(fileInput[0].files.length != 0 ){
                console.log(fileInput[0].files[0]);
                fData.append('image', fileInput[0].files[0]);
                $.ajax({
                    url: "/api/v1/avatar/user",
                    method: "POST",
                    contentType: false,
                    processData: false,
                    data: fData
                });
            }
        }
        User.update({},
            $scope.user,
            function (updatedUser) {
                $scope.user = updatedUser;
            });
    }
}]);