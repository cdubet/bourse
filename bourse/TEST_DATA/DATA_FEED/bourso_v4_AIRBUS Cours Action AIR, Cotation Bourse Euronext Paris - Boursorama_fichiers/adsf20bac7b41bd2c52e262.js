(self.webpackChunkweb_fr_portal_20=self.webpackChunkweb_fr_portal_20||[]).push([[4627],{1678:function(u,l,_){"use strict";var a=_(9299),d=_.n(a)},9299:function(u,l,_){var a=_(4692);window.brsSmartAds={countAdBlocked:function(d){window.brsSmartAds.countAdStatus(d,"0")},countAdNotBlocked:function(d){window.brsSmartAds.countAdStatus(d,"1")},countAdStatus:function(d,s){!d||!s||!window.BRS_CONFIG.app_portal_website_host||!tc_vars.user_level||a.ajax({url:"https://"+window.BRS_CONFIG.app_portal_website_host+"/comptage/adb?pageId="+encodeURI(d)+"&status="+s+"&lvl="+tc_vars.user_level,method:"GET"})},adBlocked:function(){var d=a("#sasdata");if(d.length){var s=d.data();if(s.pageName==="dashboard/home"){var e=s.markers;a.get(e.ko)}}},loadAds:function(d){var s,e,w,p,c=a("#sasdata"),o=window.sas||{};if(o.cmd=o.cmd||[],!c.length){console.warn("sas configuration not found");return}if(a(window.document).data("smartcalledformatids")||a(window.document).data("smartcalledformatids",[]),a(window.document).data("smartadserver")?e=a(window.document).data("smartadserver"):(e=c.data(),e.siteId=""+e.siteId,a(window.document).data("smartadserver",e),w={domain:e.domain,networkid:e.networkId,async:!!e.async,renderMode:0,inSequence:!0},tc_vars.user_id&&(w.uid=tc_vars.user_id),o.cmd.push(function(){o.setup(w)})),s=a(window.document).find(".smart,.smart-no-style").addBack(".smart").filter(function(){return!a(this).data("smartadserver:rendering")}),!s.length){console.warn("No smart slot to display");return}s.data("smartadserver:rendering",!0);var I="onecall",f=tc_vars.page_id;c.data("customPageName")&&(f=c.data("customPageName"));var n=e.target;if(n?n+=";":n="",document.hidden?n+="activeTab=hidden":n+="activeTab=visible",typeof d=="string"&&d.length>0&&(n+=";"+d),I==="onecall"){window.sas_unrenderedFormats=window.sas_unrenderedFormats||[];var v={},m={};a.each(s,function(r,t){var i=a(t).data("formatId");window.sas_unrenderedFormats.indexOf(i)&&window.sas_unrenderedFormats.push(i),a(t).data("hideid")&&(v[i]=a(t).data("hideid")),a(t).data("excludeFormatIds")&&(m[i]=(""+a(t).data("excludeFormatIds")).split(","))}),n=n+";nompage="+f;var h={siteId:e.siteId,pageName:f,target:n,formatId:window.sas_unrenderedFormats.join(",")};c.data("smartPageId")&&(h.pageId=c.data("smartPageId")),o.cmd.push(function(){o.call("onecall",h,{beforeRender:function(r){if(r&&r.formatId&&(v[r.formatId]&&(a("#"+v[r.formatId]).css("display","none"),a("#sas_"+r.formatId).removeClass("u-hidden")),m[r.formatId])){o.cleanAds(m[r.formatId]);var t;for(t=0;t<m[r.formatId].length;t++)a("#sas_"+m[r.formatId][t]).addClass("u-hidden")}}})})}else p={siteId:e.siteId,pageName:f,target:n},a.each(s,function(r,t){var i=a(t).data("formatId"),g=a.extend({},{formatId:i},p);a(window.document).data("smartcalledformatids").indexOf(i)<0&&(a(window.document).data("smartcalledformatids").push(i),o.cmd.push(function(){o.call("std",g)}))});if(e.pageName==="dashboard/home"){var b=e.markers;a.get(b.ok)}}}}},function(u){var l=function(a){return u(u.s=a)};u.O(0,[4692],function(){return l(1678)});var _=u.O()}]);
