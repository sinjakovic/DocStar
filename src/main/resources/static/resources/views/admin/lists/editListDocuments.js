/**
 * Created by Brandon on 4/30/2017.
 */
angular.module('Docstar.Admin.Lists').controller('Docstar.Lists.EditListDocumentsController',  ['$scope', 'Docstar.Services.FilteredList','$uibModalInstance', function( $scope, FilteredList, $uibModalInstance, selectedDocs ) {

    console.log(selectedDocs);
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

    $scope.list = {
        updatedList: selectedDocs
    };
    $scope.cancel = function() {
        $uibModalInstance.dismiss();
    };

    $scope.apply = function() {
        $uibModalInstance.close( $scope.list.updatedList );
    };

    // $scope.inList = function(docNumber){
    //     for(var i = 0; i < selectedDocs.length; i++){
    //         if(docNumber === selectedDocs[i]){
    //             return true;
    //         }
    //     }
    // }
}]);