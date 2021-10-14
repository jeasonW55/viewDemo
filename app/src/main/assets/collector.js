function sendResourceTiming(e) {
    myObj.sendResource(JSON.stringify(e))
};

function sendErrors() {
    var err = errorMonitor.getError();
    if (err.length > 0) {
        var errorInfo = {
            type: "monitor_error",
            payload: {
                url: hrefUrl,
                domain: hostname,
                uri: pathname,
                error_list: err
           }
        };
        myObj.sendError(JSON.stringify(errorInfo))
    }
}