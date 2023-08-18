import { opLastSixDays010 } from "services/LOT/op010";
import { opLastSixDays012 } from "services/LOT/op012";
import { opLastSixDays013 } from "services/LOT/op013";
import { opLastSixDays020 } from "services/LOT/op020";
import { opLastSixDays031 } from "services/LOT/op031";
import { opLastSixDays060 } from "services/LOT/op060";
import { opLastSixDays120 } from "services/LOT/rej120";
import { opLastSixDays131 } from "services/LOT/rej131";
import { opLastSixDays160 } from "services/LOT/rej160";
import { nombreOpCRA010 } from "services/CRA/op010";
import { nombreOpCRA012 } from "services/CRA/op012";
import { nombreOpCRA013 } from "services/CRA/op013";
import { nombreOpCRA020 } from "services/CRA/op020";
import { nombreOpCRA031 } from "services/CRA/op031";
import { nombreOpCRA060 } from "services/CRA/op060";
import { nombreRejCRA120 } from "services/CRA/rej120";
import { nombreRejCRA131 } from "services/CRA/rej131";
import { nombreRejCRA160 } from "services/CRA/rej160";

import { nombreOpCRL010 } from "services/CRL/op010";
import { nombreOpCRL012 } from "services/CRL/op012";
import { nombreOpCRL013 } from "services/CRL/op013";
import { nombreOpCRL020 } from "services/CRL/op020";
import { nombreOpCRL031 } from "services/CRL/op031";
import { nombreOpCRL060 } from "services/CRL/op060";
import { nombreRejCRL120 } from "services/CRL/rej120";
import { nombreRejCRL131 } from "services/CRL/rej131";
import { nombreRejCRL160 } from "services/CRL/rej160";

let refuse = [];
let accepte = [];
refuse.push(await nombreOpCRL010());
refuse.push(await nombreOpCRL012());
refuse.push(await nombreOpCRL013());
refuse.push(await nombreOpCRL020());
refuse.push(await nombreOpCRL031());
refuse.push(await nombreOpCRL060());
refuse.push(await nombreRejCRL120());
refuse.push(await nombreRejCRL131());
refuse.push(await nombreRejCRL160());

accepte.push(await nombreOpCRA010());
accepte.push(await nombreOpCRA012());
accepte.push(await nombreOpCRA013());
accepte.push(await nombreOpCRA020());
accepte.push(await nombreOpCRA031());
accepte.push(await nombreOpCRA060());
accepte.push(await nombreRejCRA120());
accepte.push(await nombreRejCRA131());
accepte.push(await nombreRejCRA160());
const nombreRefuse = await nombreOpCRL010() + await nombreOpCRL012() + await nombreOpCRL013() + await nombreOpCRL020() + await nombreOpCRL031() + await nombreOpCRL060() + await nombreRejCRL120() + await nombreRejCRL131() + await nombreRejCRL160();
const nombreAccepte = await nombreOpCRA010() + await nombreOpCRA012() + await nombreOpCRA013() + await nombreOpCRA020() + await nombreOpCRA031() + await nombreOpCRA060() + await nombreRejCRA120() + await nombreRejCRA131() + await nombreRejCRA160();
      
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

const op010 = await opLastSixDays010()
    const op012 = await opLastSixDays012()
    const op013 = await opLastSixDays013()
    const op020 = await opLastSixDays020()
    const op031 = await opLastSixDays031()
    const op060 = await opLastSixDays060()
    const rej120 = await opLastSixDays120()
    const rej131 = await opLastSixDays131()
    const rej160 = await opLastSixDays160()

var op = []
for(let i = 0; i < 7; i++){
  op.push(op010[i] + op012[i] + op013[i] + op020[i] + op031[i] + op060[i] + rej120[i] + rej131[i] + rej160[i])
}

export const barChartDataDailyTraffic = [
  {
    name: "Nombre",
    data: op,
  },
];

export const barChartOptionsDailyTraffic = {
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



export const barChartDataConsumption = [
  {
    name: "Accepté",
    data: accepte,
  },
  {
    name: "Refusé",
    data: refuse,
  },
];

export const barChartOptionsConsumption = {
  chart: {
    stacked: true,
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
    categories: ["010", "012", "013", "020", "031", "060", "120", "131", "160"],
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
      show: false,
      style: {
        colors: "#A3AED0",
        fontSize: "14px",
        fontWeight: "500",
      },
    },
  },

  grid: {
    borderColor: "rgba(163, 174, 208, 0.3)",
    show: true,
    yaxis: {
      lines: {
        show: false,
        opacity: 0.5,
      },
    },
    row: {
      opacity: 0.5,
    },
    xaxis: {
      lines: {
        show: false,
      },
    },
  },
  fill: {
    type: "solid",
    colors: ["#5E37FF", "#6AD2FF"],
  },
  legend: {
    show: false,
  },
  colors: ["#5E37FF", "#6AD2FF"],
  dataLabels: {
    enabled: false,
  },
  plotOptions: {
    bar: {
      borderRadius: 10,
      columnWidth: "20px",
    },
  },
};

export const pieChartOptions = {
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

export const pieChartData = [nombreAccepte, nombreRefuse];

