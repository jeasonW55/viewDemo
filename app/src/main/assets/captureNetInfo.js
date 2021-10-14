function () {
        let t = window.performance.timing;
        const dnsTime = t.domainLookupEnd - t.domainLookupStart;
        const tcpTime = t.connectEnd - t.connectStart;
        const sslTime = t.connectEnd - t.secureConnectionStart;
        const ttfbTime = t.responseStart - t.requestStart;
        const dataTransfer = t.responseEnd - t.responseStart;
        const domAnalysis = t.domInteractive - t.responseEnd;
        const resourceLoad = t.loadEventStart - t.domContentLoadedEventEnd;
        const firstPackage = t.responseStart - t.domainLookupStart;
        const whiteScreen = t.responseEnd - t.fetchStart;
        const firstCanInteraction = t.domInteractive - t.fetchStart;
        const domReady = t.domContentLoadedEventEnd - t.fetchStart;
        const pageLoadComplete = t.loadEventStart - t.fetchStart;
        const totalLoadComplete = t.loadEventEnd - t.navigationStart;
        return {
            dnsTime: dnsTime,
            tcpTime: tcpTime,
            sslTime: t.secureConnectionStart === 0 ? -1: sslTime,
            ttfbTime: ttfbTime,
            dataTransfer: dataTransfer,
            domAnalysis: domAnalysis,
            resourceLoad: resourceLoad,
            firstPackage: firstPackage,
            whiteScreen: whiteScreen,
            firstCanInteraction: firstCanInteraction,
            domReady: domReady,
            pageLoadComplete: pageLoadComplete,
            totalLoadComplete: totalLoadComplete,
            loadEventEnd: t.loadEventEnd,
            };
        };