var dollar = 1;
var quarter = 0.25;
var dime = 0.1;
var nickel = 0.05;
var penny = 0.01;
var qCounter = 0; 
var diCounter = 0;
var nCounter = 0;
var pCounter = 0;
var holder = 0.00;
$(document).ready(function(){
	load();
	$("#dollar").click(function(){
		holder += dollar;
		sum(holder);
	});
	
	$("#quarter").click(function(){
		holder += quarter;
		sum(holder);
	});
	
	$("#dime").click(function(){
		holder += dime;
		sum(holder);
	});
	
	$("#nickel").click(function(){
		holder += nickel;
		sum(holder);
	});
	
	$("#purchase").click(purchase);
	
	$("#change").click(function(){
		if($(".amountEntered").val() == 0.00) {
			$("#changeBox").val("0");
			$("#item").val("");
			$("#message").val("");
		} else {
			changeBox(holder);
			$("#item").val("");
			$("#message").val("");
			$("#changeBox").val("Q: "+ qCounter +"   D: "+ diCounter +"   N: "+ nCounter +"   P: "+ pCounter +"");
			$(".amountEntered").val("0.00");
			holder = 0.00;
		}
	});
});

function load() {

	$.ajax({
		type : 'GET',
		url : 'http://tsg-vending.herokuapp.com/items',
		success : function(data) {
			$.each(data, function(index,item){
				var d = "<button type='button' class='item btn col-3' onclick='itemId(" + item.id + ")' value="+ item.price +" id="+ item.id +">";
				d += "<p class='itemIds'>"+ (item.id) + "</p>";
				d += "<p class='itemStuff'>"+ item.name + "</p>";
				d += "<p class='itemStuff'>$"+ item.price + "</p><br/>";
				d += "<p class='itemStuff'>Quantity left: "+ item.quantity + "</p>";
				d += "</button>"
				$(".items").append(d);
			});
		},
		error : function(jqXHR) {
			console.log(jqXHR);
		}
	});
}

function purchase() {
	var money = $(".amountEntered").val();
	//var itemCost = $(this).val();
	//var change = (money - itemCost).toFixed(2);
	//changeBox(change);
	$.ajax({
		type : "POST",
		url : "	http://tsg-vending.herokuapp.com/money/"+ money +"/item/"+ $("#item").val() +"",
		success : function(data) {
			$("#message").val("Thank You!");
			$("#changeBox").val("Q: "+ data.quarters +"   D: "+ data.dimes +"   N: "+ data.nickels +"   P: "+ data.pennies +"");
			$(".amountEntered").val("0.00");
			$("#item").val("");
			holder = 0;
			
		},
		error : function(xhr) {
			console.log(xhr.responseText);
			var resMessage = xhr.responseText;
				$("#message").val(resMessage.substring(12, (resMessage.length - 2)));
		}
	});
}

function sum(holder) {
	$(".amountEntered").val(holder.toFixed(2));
}

function itemId(id) {
	$("#item").val(id);
	$("#purchase").val($("#"+ id +"").val());
	$("#message").val("");
}

function changeBox(money) {
	var leftOver = money;
	qCounter = 0;
	diCounter = 0;
	nCounter = 0;
	pCounter = 0;
	while(leftOver > 0.00) {
		if(leftOver >= quarter)  {
			leftOver -= quarter;
			leftOver = leftOver.toFixed(2) * 100/100;
			qCounter++;
		} else if(leftOver >= dime) {
			leftOver -= dime;
			leftOver = leftOver.toFixed(2) * 100/100;
			diCounter++;
		} else if (leftOver >= nickel){
			leftOver -= nickel;
			leftOver = leftOver.toFixed(2) * 100/100;
			nCounter++;
		} else {
			leftOver -= penny;
			leftOver = leftOver.toFixed(2) * 100/100;
			pCounter++;
		}
		
	};
}


