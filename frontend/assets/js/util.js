import Vue from 'vue'

const util = {
	
	//remove the highlight of the last table data selected
	resetActive(data){
		if(data != null){
			Vue.set(data,"_rowVariant","");
		}
	},
	
	//set the highlight of the table data selected
	setActive(data,name){
		if(name == "変更" || name == ''){
			Vue.set(data, "_rowVariant", "info");
		}
	},

}

export default util