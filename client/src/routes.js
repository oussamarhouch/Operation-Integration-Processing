import React from "react";

import { Icon } from "@chakra-ui/react";
import { MdHome } from "react-icons/md";
import {FaMoneyBillTransfer} from "react-icons/fa6";
import {TbWorldDollar} from "react-icons/tb";
import {LiaFileContractSolid} from "react-icons/lia";
import {LuClipboardSignature} from "react-icons/lu";
import {HiOutlineReceiptRefund} from "react-icons/hi";
import {BsReceiptCutoff} from "react-icons/bs";
import {TbWallpaperOff} from "react-icons/tb";
import MainDashboard from "views/admin/default";
import op010 from "views/admin/op010";
import op012 from "views/admin/op012";
import op013 from "views/admin/op013";
import op020 from "views/admin/op020";
import op031 from "views/admin/op031";
import op060 from "views/admin/op060";
import rej120 from "views/admin/rej120";
import rej131 from "views/admin/rej131";
import rej160 from "views/admin/rej160";
import { GiBanknote } from "react-icons/gi";
import{ TbCashBanknote} from "react-icons/tb"
import {MdOutlinePayments} from 'react-icons/md'

import SignInCentered from "views/auth/signIn";

const routes = [
  
  {
    name: "login",
    layout: "/auth",
    path: "",
    icon: <Icon as={GiBanknote} width="20px" height="20px" color="inherit" />,
    component: SignInCentered,
  },
  {
    name: "Acceuil",
    layout: "/admin",
    path: "/default",
    icon: <Icon as={MdHome} width='20px' height='20px' color='inherit' />,
    component: MainDashboard,
  },
  {
    name: "Virement Standard",
    layout: "/admin",
    path: "/op010",
    icon: <Icon as={MdOutlinePayments} width='20px' height='20px' color='inherit' />,
    component: op010,
  },
  {
    name: "Virement de Régularisation",
    layout: "/admin",
    icon: <Icon as={FaMoneyBillTransfer} width='20px' height='20px' color='inherit' />,
    path: "/op012",
    component: op012,
  },
  {
    name: "virement de l’Etranger",
    layout: "/admin",
    path: "/op013",
    icon: <Icon as={TbWorldDollar} width='20px' height='20px' color='inherit' />,
    component: op013,
  },
  {
    name: "Ordre de Prélèvement",
    layout: "/admin",
    path: "/op020",
    icon: <Icon as={LuClipboardSignature} width='20px' height='20px' color='inherit' />,
    component: op020,
  },
  {
    name: "Chèque",
    layout: "/admin",
    path: "/op031",
    icon: <Icon as={TbCashBanknote}  width='20px' height='20px' color='inherit' />,
    component: op031,
  },
  {
    name: "Lettre de Change",
    layout: "/admin",
    path: "/op060",
    icon: <Icon as={LiaFileContractSolid} width='20px' height='20px' color='inherit' />,
    component: op060,
  },
  {
    name: "Rejet de Prélèvement",
    layout: "/admin",
    path: "/rej120",
    icon: <Icon as={HiOutlineReceiptRefund} width='20px' height='20px' color='inherit' />,
    component: rej120,
  },
  {
    name: "Rejet de Chèque",
    layout: "/admin",
    path: "/rej131",
    icon: <Icon as={BsReceiptCutoff} width='20px' height='20px' color='inherit' />,
    component: rej131,
  },
  {
    name: "Rejet de Lettre de Change",
    layout: "/admin",
    path: "/rej160",
    icon: <Icon as={TbWallpaperOff} width='20px' height='20px' color='inherit' />,
    component: rej160,
  },
];

export default routes;
