/**
 * Created by Brandon on 4/21/2017.
 */

var adminDocuments = angular.module('Docstar.Admin.Documents', ['ngRoute', 'ngResource', 'ui.bootstrap', 'Session']);

adminDocuments.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/admin/documents', {
        templateUrl: 'resources/views/admin/documents/documents.html',
        controller: 'Docstar.Admin.Documents.Controller'
    })
        .when('/admin/documents/:dID', {
            templateUrl : 'resources/views/admin/documents/details.html',
            controller : 'Docstar.Admin.Documents.Details.Controller'
        });
}]);

// docs.controller('Docstar.Admin.Documents.Controller', ['$scope', '$resource', '$routeParams', '$uibModal',  'Session.Service', function( $scope, $resource, $routeParams, $modal, Session ) {
//     var Docs = $resource('api/v1/admin/documents', {});
//
//     $scope.docs = Docs.get({});
//     $scope.role = 'admin';
// }]);

docs.controller('Docstar.Admin.Documents.Controller', ['$scope', 'Docstar.Services.FilteredList', function( $scope, FilteredList ) {
    var config = {
        route : '/api/v1/admin/documents/:dID',
        filter : [
            { name : 'type', type : 'string', defaultValue : undefined },
            { name : 'significance', type : 'flag', defaultValue : undefined },
            { name : 'title', type : 'string', defaultValue : undefined },
            { name : 'zip', type : 'string', defaultValue : undefined }
        ]
    };
    FilteredList.config( config );
    $scope.service = FilteredList;
    $scope.role = 'admin';
}]);