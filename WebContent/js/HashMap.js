/**
 * HashMap 
 * http://lpicquet.wordpress.com/2009/07/08/javascript-hashmap/
 */

function HashMap(){
    HashMap.prototype.keys = new Array();
    HashMap.prototype.values = new Array();
}
/* HashMap get function */
HashMap.prototype.get= function(key){
    for (var index=0; index < this.keys.length; index++){
        if (key==this.keys[index]){
            return(this.values[index]);
        }
    }
    return(null);
};
/* HashMap put function */
HashMap.prototype.put = function(key, value){
    this.keys[this.keys.length] = key;
    this.values[this.values.length] = value;
};
/* HashMap containsKey function */
HashMap.prototype.containsKey= function(key){
    for (var index=0; index < this.keys.length; index++){
        if (key==this.keys[index]){
            return(true);
        }
    }
    return(false);
};
/* HashMap debug function */
HashMap.prototype.debug = function(){
    var debug="";
    for (var index=0; index < this.keys.length; index++){
        var key=this.keys[index];
        var value=this.values[index];
        debug+=key + "->" + value + "\n";
    }
    return(debug);
};
/* HashMap size function */
HashMap.prototype.size = function(){
    return(this.keys.length);
};
/* HashMap containsKey function */
HashMap.prototype.containsValue= function(values){
    for (var index=0; index < this.values.length; index++){
        if (values==this.values[index]){
            return(true);
        }
    }
    return(false);
};