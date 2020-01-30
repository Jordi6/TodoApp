$(function() {
	var editId = 0;

	getTodos();
	

	$("main").on("click", ".del-btn", delelteTodos);
	$("main").on("click", ".cbox", completed);
	$("#btn").click(saveTodo);
	$("main").on("click", ".edit-todo", editTodo);
	
	$("#sign-up-btn").click(showForm);
	
	
	//function to show sign up form
	function showForm() {
		$(this).parent().find("#sign-up-form").addClass("show");
	}
	
	
	//function to edit my todo
	function editTodo() {
		var text = $(this).parent().find(".item-text").text();
		$("input[name=todo]").val(text);
		
		
		var id = $(this).parent().find(".item-text").data("id");
		editId = id;
	}
	
	
	//function to style on check
	function completed() {
		// add if else logic if checkd else 
		var text = $(this).parent().find(".item-text");
		text.toggleClass("strike");
	}
	
	//function to reaload my todos on delete and once user adds new todo
	function reloadTodos() {
		//for when I add pageable offset = 0;
		$(".items").remove();
		getTodos();
		clearInput();
		editId = 0;
	}
	
	
	
	//function to delted todo clicked
	function delelteTodos() {
		var id = $(this).parent().find(".item-text").data("id");
		$.ajax({
			url: "/delete-todos",
			method: "post",
			dataType: "json",
			data: {
				id: id
			},
			error: ajaxError,
			success: function(data) {
				//console.log(data);
				reloadTodos();
			}
		});
		
		
	}
	
	
	// function call that returns user input and resets the input
	function clearInput() {
		$("input[name=todo]").val("");
	}

	
	//send todo data to repo and update database;
	function saveTodo() {
		var inputValue = $("input[name=todo]").val();
		if (inputValue == ""){
			alert("Please enter your todo");
		}else {
		$.ajax({
			url: "/save-todos",
			method: "get",
			dataType: "json",
			data: {
				completed: false,
				content: inputValue, 
				id: editId
			},
			error: ajaxError,
			success: function(data) {
				//console.log(data);
				reloadTodos();
			}
		});
	  }
	}
	
	
	
	//get todos from data base;
	function getTodos() {
		$.ajax({
			url: "/get-todos",
			method: "get",
			dataType: "json",
			data: {
				
			},
			error: ajaxError,
			success: function(data) {
				//console.log(data);
				buildTodos(data);
			}
		});
	}
	

	function ajaxError() {
		alert("ajax error!");
	}

	
	//build UI with the data
	function buildTodos(data) {
		$(".todos").remove();
		for(var i = 0;i<data.length;i++) {
			var $todoTemplate = $('#template-task').clone();
			$todoTemplate.removeAttr("id");
			$todoTemplate.addClass("items");
			
			$todoTemplate.find(".cbox").data("complete",data[i].completed);
			$todoTemplate.find(".cbox").attr("id", "cb");
		
			
			if ($todoTemplate.find(".cbox").data("complete")) {
					$todoTemplate.find(".cbox").prop('checked',true);
			}else {
					$todoTemplate.find(".cbox").prop('checked',false);	
			}
			
			//add code to controller to check right user 
//			if (!data[i].editable) {
//				$todoTemplate.find(".edit-todo").hide();
//			}
			$todoTemplate.find(".edit-todo").data("id",data[i].id);
			
			$todoTemplate.find(".item-text").append(data[i].content);
			
			//give id to the content
			$todoTemplate.find(".item-text").data("id", data[i].id);
			
			$todoTemplate.find(".del-btn").addClass("btn");
			$("main").append($todoTemplate);
		}
	}
	
	
	
	
});