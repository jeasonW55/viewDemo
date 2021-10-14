package com.example.viewdemo.data.JsMonitorData;

import java.util.List;

/**
 * <pre>
 *   author: wangjishun
 *   time: 2021/08/04
 *   desc:
 * </pre>
 **/
public class JsMonitorData {
    public String type;

    public PayLoad payload;

    @Override
    public String toString() {
        return "JsMonitorData{" +
                "type='" + type + '\'' +
                ", payLoad=" + payload +
                '}';
    }

    public static class PayLoad {
        public String url;
        public String domain;
        public String uri;
        public NavigationTiming navigationTiming;
        public List<ResourceTiming> resourceTiming;

        @Override
        public String toString() {
            return "PayLoad{" +
                    "url='" + url + '\'' +
                    ", domain='" + domain + '\'' +
                    ", uri='" + uri + '\'' +
                    ", navigationTiming=" + navigationTiming +
                    ", resourceTiming=" + resourceTiming +
                    '}';
        }

        public static class NavigationTiming {
            public String navigationStart;
            public String redirectStart;
            public String redirectEnd;
            public String fetchStart;
            public String domainLookupStart;
            public String domainLookupEnd;
            public String connectStart;
            public String responseStart;
            public String responseEnd;
            public String unloadEventStart;
            public String unloadEventEnd;
            public String domLoading;
            public String domInteractive;
            public String domContentLoadedEventStart;
            public String domContentLoadedEventEnd;
            public String domComplete;
            public String loadEventStart;
            public String loadEventEnd;
            public String pageTime;

            @Override
            public String toString() {
                return "NavigationTiming{" +
                        "navigationStart='" + navigationStart + '\'' +
                        ", redirectStart='" + redirectStart + '\'' +
                        ", redirectEnd='" + redirectEnd + '\'' +
                        ", fetchStart='" + fetchStart + '\'' +
                        ", domainLookupStart='" + domainLookupStart + '\'' +
                        ", domainLookupEnd='" + domainLookupEnd + '\'' +
                        ", connectStart='" + connectStart + '\'' +
                        ", responseStart='" + responseStart + '\'' +
                        ", responseEnd='" + responseEnd + '\'' +
                        ", unloadEventStart='" + unloadEventStart + '\'' +
                        ", unloadEventEnd='" + unloadEventEnd + '\'' +
                        ", domLoading='" + domLoading + '\'' +
                        ", domInteractive='" + domInteractive + '\'' +
                        ", domContentLoadedEventStart='" + domContentLoadedEventStart + '\'' +
                        ", domContentLoadedEventEnd='" + domContentLoadedEventEnd + '\'' +
                        ", domComplete='" + domComplete + '\'' +
                        ", loadEventStart='" + loadEventStart + '\'' +
                        ", loadEventEnd='" + loadEventEnd + '\'' +
                        ", pageTime='" + pageTime + '\'' +
                        '}';
            }
        }

        public static class ResourceTiming {
            public String connectEnd;
            public String connectStart;
            public String domainLookupEnd;
            public String domainLookupStart;
            public String duration;
            public String entryType;
            public String fetchStart;
            public String initiatorType;
            public String name;
            public String redirectEnd;
            public String requestStart;
            public String responseEnd;
            public String responseStart;
            public String secureConnectionStart;
            public String startTime;

            @Override
            public String toString() {
                return "ResourceTiming{" +
                        "connectEnd='" + connectEnd + '\'' +
                        ", connectStart='" + connectStart + '\'' +
                        ", domainLookupEnd='" + domainLookupEnd + '\'' +
                        ", domainLookupStart='" + domainLookupStart + '\'' +
                        ", duration='" + duration + '\'' +
                        ", entryType='" + entryType + '\'' +
                        ", fetchStart='" + fetchStart + '\'' +
                        ", initiatorType='" + initiatorType + '\'' +
                        ", name='" + name + '\'' +
                        ", redirectEnd='" + redirectEnd + '\'' +
                        ", requestStart='" + requestStart + '\'' +
                        ", responseEnd='" + responseEnd + '\'' +
                        ", responseStart='" + responseStart + '\'' +
                        ", secureConnectionStart='" + secureConnectionStart + '\'' +
                        ", startTime='" + startTime + '\'' +
                        '}';
            }
        }
    }
}
