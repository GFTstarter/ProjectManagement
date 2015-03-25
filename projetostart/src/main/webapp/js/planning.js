$(document).ready(function() {
	
    $("#vertical").kendoSplitter({
        orientation: "vertical",
        panes: [
            { collapsible: false },
            { collapsible: false, size: "100px" },
            { collapsible: false, resizable: false, size: "100px" }
        ]
    });

    $("#horizontal").kendoSplitter({
        panes: [
            { collapsible: true, size: "220px" },
            { collapsible: false },
            { collapsible: true, size: "220px" }
        ]
    });
	
	var Weapon = kendo.data.Model.define({
		id : "wid"
	})
	
	var dataSource = new kendo.data.DataSource({
		transport : {
			read : {
				url: "views/weapons.json"
			},
			create:{
				url: "views/create.json",
				type: "POST"
				}
			},
			schema : {
				model : Weapon
			}
		});
	
	var selectedWeapon;
		
	$("#weapons").kendoGrid({
		dataSource : dataSource,
		selectable: true,
		change: function(){
			/*var id = this.select().data("wid");
			selectedWeapon = this.dataSource.get(id);
			
			console.log(data("wid"));
			
			$("#change-name").val(selectedWeapon.get("name"));
			$("#change-description").val(selectedWeapon.get("description"));*/
			
		}
	});

	$("#create-weapon").click(function() {
		
		dataSource.add({
			name : $("#create-name").val(),
			description: $("#create-description").val()
		});
		
		dataSource.sync();
		
		$("#create-name").val('');
		$("#create-description").val('');
		
		//dataSource.read();
	});
});
