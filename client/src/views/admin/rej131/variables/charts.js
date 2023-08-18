import { rejetRefuseListLastSixDays } from "services/CRL/rej131";
import {rejetAccepteListLastSixDays} from "services/CRA/rej131";
import {nombreRejetListLastSevenDays} from "services/LOT/rej131";
import { nombreRejCRA } from "services/CRA/rej131";
import { nombreRejCRL } from "services/CRL/rej131";

var rejetRefuse = await rejetRefuseListLastSixDays();
var rejetAccepte = await rejetAccepteListLastSixDays()
var nombreRejet = await nombreRejetListLastSevenDays();
var nombreOperationCRL = await nombreRejCRL();
var nombreOperationCRA = await nombreRejCRA();


function getLastSixDays() {
  const today = new Date();
  const lastSixDays = [];

  for (let i = 5; i >= 0; i--) {
    const day = new Date(today);
    day.setDate(today.getDate() - i);
    const formattedDay = ("0" + day.getDate()).slice(-2);
    const formattedMonth = ("0" + (day.getMonth() + 1)).slice(-2);
    const formattedDate = `${formattedDay}/${formattedMonth}`;
    lastSixDays.push(formattedDate);
  }

  return lastSixDays;
}

const lineChartDataTotalSpent = [
  {
    name: "Accepté",
    data: rejetAccepte,
  },
  {
    name: "Refusé",
    data: rejetRefuse,
  },
];

const lineChartOptionsTotalSpent = {
  chart: {
    toolbar: {
      show: false,
    },
    dropShadow: {
      enabled: true,
      top: 13,
      left: 0,
      blur: 10,
      opacity: 0.1,
      color: "#4318FF",
    },
  },
  colors: ["#4318FF", "#39B8FF"],
  markers: {
    size: 0,
    colors: "white",
    strokeColors: "#7551FF",
    strokeWidth: 3,
    strokeOpacity: 0.9,
    strokeDashArray: 0,
    fillOpacity: 1,
    discrete: [],
    shape: "circle",
    radius: 2,
    offsetX: 0,
    offsetY: 0,
    showNullDataPoints: true,
  },
  tooltip: {
    theme: "dark",
  },
  dataLabels: {
    enabled: false,
  },
  stroke: {
    curve: "smooth",
    type: "line",
  },
  xaxis: {
    type: "numeric",
    categories: getLastSixDays(),
    labels: {
      style: {
        colors: "#A3AED0",
        fontSize: "12px",
        fontWeight: "500",
      },
    },
    axisBorder: {
      show: false,
    },
    axisTicks: {
      show: false,
    },
  },
  yaxis: {
    show: false,
  },
  legend: {
    show: false,
  },
  grid: {
    show: false,
    column: {
      color: ["#7551FF", "#39B8FF"],
      opacity: 0.5,
    },
  },
  color: ["#7551FF", "#39B8FF"],
};


function getLastSevenDays() {
  const today = new Date();
  const lastSixDays = [];

  for (let i = 6; i >= 0; i--) {
    const day = new Date(today);
    day.setDate(today.getDate() - i);
    const formattedDay = ("0" + day.getDate()).slice(-2);
    const formattedDate = `${formattedDay}`;
    lastSixDays.push(formattedDate);
  }

  return lastSixDays;
}

const barChartDataDailyTraffic = [
  {
    name: "nbr Rejets",
    data: nombreRejet,
  },
];

const barChartOptionsDailyTraffic = {
  chart: {
    toolbar: {
      show: false,
    },
  },
  tooltip: {
    style: {
      fontSize: "12px",
      fontFamily: undefined,
    },
    onDatasetHover: {
      style: {
        fontSize: "12px",
        fontFamily: undefined,
      },
    },
    theme: "dark",
  },
  xaxis: {
    categories: getLastSevenDays(),
    show: false,
    labels: {
      show: true,
      style: {
        colors: "#A3AED0",
        fontSize: "14px",
        fontWeight: "500",
      },
    },
    axisBorder: {
      show: false,
    },
    axisTicks: {
      show: false,
    },
  },
  yaxis: {
    show: false,
    color: "black",
    labels: {
      show: true,
      style: {
        colors: "#CBD5E0",
        fontSize: "14px",
      },
    },
  },
  grid: {
    show: false,
    strokeDashArray: 5,
    yaxis: {
      lines: {
        show: true,
      },
    },
    xaxis: {
      lines: {
        show: false,
      },
    },
  },
  fill: {
    type: "gradient",
    gradient: {
      type: "vertical",
      shadeIntensity: 1,
      opacityFrom: 0.7,
      opacityTo: 0.9,
      colorStops: [
        [
          {
            offset: 0,
            color: "#4318FF",
            opacity: 1,
          },
          {
            offset: 100,
            color: "rgba(67, 24, 255, 1)",
            opacity: 0.28,
          },
        ],
      ],
    },
  },
  dataLabels: {
    enabled: false,
  },
  plotOptions: {
    bar: {
      borderRadius: 10,
      columnWidth: "40px",
    },
  },
};

const pieChartOptions = {
  labels: ["Accepté", "Refusé"],
  colors: ["#4318FF", "#6AD2FF"],
  chart: {
    width: "50px",
  },
  states: {
    hover: {
      filter: {
        type: "none",
      },
    },
  },
  legend: {
    show: false,
  },
  dataLabels: {
    enabled: false,
  },
  hover: { mode: null },
  plotOptions: {
    donut: {
      expandOnClick: false,
      donut: {
        labels: {
          show: false,
        },
      },
    },
  },
  fill: {
    colors: ["#4318FF", "#6AD2FF"],
  },
  tooltip: {
    enabled: true,
    theme: "dark",
  },
};

const pieChartData = [nombreOperationCRA, nombreOperationCRL];




export { lineChartDataTotalSpent, lineChartOptionsTotalSpent, barChartDataDailyTraffic, barChartOptionsDailyTraffic, pieChartData, pieChartOptions };
