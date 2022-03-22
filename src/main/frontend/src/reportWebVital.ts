import {ReportHandler} from "web-vitals";

const reportWebVital = (onPerformanceEntry?: ReportHandler) => {
    if (onPerformanceEntry) {
        import ("web-vitals")
            .then(({getCLS, getFID, getFCP, getLCP, getTTFB}) => {
                getCLS(onPerformanceEntry);
                getFID(onPerformanceEntry);
                getFCP(onPerformanceEntry);
                getLCP(onPerformanceEntry);
                getTTFB(onPerformanceEntry);
            });
    }
};

export default reportWebVital;
