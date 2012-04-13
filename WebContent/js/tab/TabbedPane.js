/*
 * TabbedPane.js v1.0.0 -- Dynamic AJAX Tabs for Prototype
 * Copyright (c) 2007-2008 Jesse Farmer <jesse@20bits.com>
 * Licensed under the MIT license.
 */
Hash.prototype.toObject = Hash.prototype.toObject || function() { return Object.clone(this); }

var TabbedPane = function(pane, page_urls, args) {
	var args = $H({asynchronous: true, method: 'get', evalScripts: true, parameters:''}).merge(args).toObject();	
	
	this.ActiveTab = '';
	
	this.loadPage = function(page_id) {
		this.ActiveTab = page_id;
		new Ajax.Updater(pane, page_urls[page_id], args);
	}
	this.changeTab = function(page_id,changeUrl){
		for (_page_id in page_urls) {
			$(_page_id).removeClassName('current');
		}
		$(page_id).up().up().addClassName('current').id;
		
		this.ActiveTab = page_id;
		if(changeUrl) {
			new Ajax.Updater(pane,changeUrl, args);
		} else {
			new Ajax.Updater(pane, page_urls[page_id], args);
		}
	}  
	for (page_id in page_urls) {
		Event.observe(page_id, 'click', function(e) {
			if ('function' == typeof(args.onClick))
				args.onClick(e);

			for (page_id in page_urls) $(page_id).removeClassName('current');
			this.loadPage(Event.element(e).up().up().addClassName('current').id);
			Event.stop(e);
		}.bindAsEventListener(this));

		if ($(page_id).hasClassName('current')) { this.loadPage(page_id); }
	}
}